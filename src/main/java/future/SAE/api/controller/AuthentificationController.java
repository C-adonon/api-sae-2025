package future.SAE.api.controller;

import future.SAE.api.dto.reponse.JwtDTOReponse;
import future.SAE.api.dto.reponse.UtilisateurReponse;
import future.SAE.api.dto.requete.AuthentificationRequete;
import future.SAE.api.dto.requete.InscriptionEleveRequete;
import future.SAE.api.dto.requete.InscriptionProfesseurRequete;
import future.SAE.api.mapping.UtilisateurDTOMapper;
import future.SAE.application.interfaces.IAuthentificationService;
import future.SAE.application.interfaces.IInscriptionService;
import future.SAE.application.services.AuthentificationService;
import future.SAE.application.services.InscriptionService;
import future.SAE.domain.model.Utilisateur;
import future.SAE.infrastructure.config.JwtTokenProvider;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentification", description = "API de gestion des inscriptions et de la connexion des utilisateurs")
public class AuthentificationController {

    private final IInscriptionService inscriptionService;
    private final IAuthentificationService authentificationService;
    private final UtilisateurDTOMapper dtoMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthentificationController(
            InscriptionService inscriptionService,
            AuthentificationService authentificationService,
            UtilisateurDTOMapper dtoMapper,
            JwtTokenProvider jwtTokenProvider) {
        this.inscriptionService = inscriptionService;
        this.authentificationService = authentificationService;
        this.dtoMapper = dtoMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/inscription/professeur")
    @Operation(summary = "Inscrire un nouveau professeur", description = "Crée un compte Professeur. L'email et l'identifiant doivent être uniques.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Professeur créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Erreur de validation des champs",
                    content = @Content(mediaType = "application/json", schema = @Schema(example = "{\n  \"email\": \"Le format de l'email est invalide\",\n  \"motDePasse\": \"Le mot de passe doit faire au moins 8 caractères\"\n}"))),
            @ApiResponse(responseCode = "409", description = "Conflit : L'identifiant ou l'email est déjà utilisé",
                    content = @Content(mediaType = "application/json", schema = @Schema(example = "{\n  \"erreur\": \"L'identifiant 'P-2026-001' est déjà attribué à un autre utilisateur.\"\n}")))
    })
    public ResponseEntity<UtilisateurReponse> inscrireProfesseur(@Valid @RequestBody InscriptionProfesseurRequete requete) {
        Utilisateur nouveauProf = inscriptionService.inscrireProfesseur(
                requete.getIdentifiant(), requete.getNom(), requete.getPrenom(),
                requete.getEmail(), requete.getMotDePasse()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(nouveauProf));
    }

    @PostMapping("/inscription/eleve")
    @Operation(summary = "Inscrire un nouvel élève", description = "Crée un compte Élève. L'email et l'identifiant doivent être uniques.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Élève créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Erreur de validation des champs",
                    content = @Content(mediaType = "application/json", schema = @Schema(example = "{\n  \"email\": \"Le format de l'email est invalide\",\n  \"motDePasse\": \"Le mot de passe doit faire au moins 8 caractères\"\n}"))),
            @ApiResponse(responseCode = "409", description = "Identifiant ou email déjà pris",
                    content = @Content(mediaType = "application/json", schema = @Schema(example = "{\n  \"erreur\": \"L'identifiant 'Cet identifiant est déjà utilisé.' est déjà attribué à un autre utilisateur.\"\n}")))
    })
    public ResponseEntity<UtilisateurReponse> inscrireEleve(@Valid @RequestBody InscriptionEleveRequete requete) {
        Utilisateur nouvelEleve = inscriptionService.inscrireEleve(
                requete.getIdentifiant(), requete.getNom(), requete.getPrenom(),
                requete.getEmail(), requete.getMotDePasse()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(nouvelEleve));
    }

    @PostMapping("/connexion")
    @Operation(summary = "Se connecter", description = "Authentifie l'utilisateur et renvoie un token JWT d'accès.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Connexion réussie, token généré"),
            @ApiResponse(responseCode = "401", description = "Identifiants incorrects",
                    content = @Content(mediaType = "application/json", schema = @Schema(example = "{\n  \"erreur\": \"L'identifiant ou le mot de passe est incorrect.\"\n}")))
    })
    public ResponseEntity<JwtDTOReponse> connecter(@Valid @RequestBody AuthentificationRequete requete) {
        Utilisateur utilisateurConnecte = authentificationService.authentifier(
                requete.getIdentifiant(), requete.getMotDePasse()
        );

        String token = jwtTokenProvider.genererToken(utilisateurConnecte);

        return ResponseEntity.ok(new JwtDTOReponse(token));
    }
}