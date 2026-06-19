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

import io.swagger.v3.oas.annotations.Operation;
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
    private final future.SAE.infrastructure.security.JwtTokenProvider jwtTokenProvider; // 👈 1. Ajout du provider

    public AuthentificationController(
            InscriptionService inscriptionService,
            AuthentificationService authentificationService,
            UtilisateurDTOMapper dtoMapper,
            future.SAE.infrastructure.security.JwtTokenProvider jwtTokenProvider) { // 👈 2. Injection dans le constructeur
        this.inscriptionService = inscriptionService;
        this.authentificationService = authentificationService;
        this.dtoMapper = dtoMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/inscription/professeur")
    @Operation(summary = "Inscrire un nouveau professeur", description = "Crée un compte Professeur. L'email et l'identifiant doivent être uniques.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Professeur créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Erreur de validation (ex: email invalide ou mot de passe trop court)"),
            @ApiResponse(responseCode = "409", description = "Conflit : L'identifiant ou l'email est déjà utilisé")
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
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "409", description = "Identifiant ou email déjà pris")
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
            @ApiResponse(responseCode = "401", description = "Identifiants incorrects")
    })

    public ResponseEntity<JwtDTOReponse> connecter(@Valid @RequestBody AuthentificationRequete requete) {
        // L'AuthentificationService vérifie si le mot de passe correspond au hash en BD
        Utilisateur utilisateurConnecte = authentificationService.authentifier(
                requete.getIdentifiant(), requete.getMotDePasse()
        );

        // Token
        String token = jwtTokenProvider.genererToken(utilisateurConnecte);

        return ResponseEntity.ok(new JwtDTOReponse(token));
    }
}