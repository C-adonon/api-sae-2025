package future.SAE.api.controller;

import future.SAE.api.dto.reponse.UtilisateurReponse;
import future.SAE.api.dto.requete.MiseAJourUtilisateurRequete;
import future.SAE.api.mapping.UtilisateurDTOMapper;
import future.SAE.application.interfaces.IUtilisateurService;
import future.SAE.domain.model.Utilisateur;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/utilisateurs")
@Tag(name = "Utilisateurs", description = "API de consultation et de modification des profils utilisateurs")
public class UtilisateurController {

    private final IUtilisateurService utilisateurService;
    private final UtilisateurDTOMapper dtoMapper;

    public UtilisateurController(IUtilisateurService utilisateurService, UtilisateurDTOMapper dtoMapper) {
        this.utilisateurService = utilisateurService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un profil", description = "Récupère les informations publiques d'un utilisateur (Professeur ou Élève) via son identifiant unique (UUID).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profil récupéré avec succès"),
            @ApiResponse(responseCode = "404", description = "Utilisateur introuvable",
                    content = @Content(mediaType = "application/json", schema = @Schema(example = "{\n  \"erreur\": \"Utilisateur introuvable avec l'ID : 123e4567-e89b-12d3-a456-426614174000\"\n}")))
    })
    public ResponseEntity<UtilisateurReponse> consulterProfil(@PathVariable UUID id) {
        Utilisateur utilisateur = utilisateurService.consulterProfil(id);

        return ResponseEntity.ok(dtoMapper.toResponse(utilisateur));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un profil", description = "Met à jour le nom et le prénom d'un utilisateur existant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profil mis à jour avec succès"),
            @ApiResponse(responseCode = "400", description = "Données de mise à jour invalides (ex: nom vide)",
                    content = @Content(mediaType = "application/json", schema = @Schema(example = "{\n  \"nom\": \"Le nom est obligatoire\",\n  \"prenom\": \"Le prénom est obligatoire\"\n}"))),
            @ApiResponse(responseCode = "404", description = "Utilisateur introuvable",
                    content = @Content(mediaType = "application/json", schema = @Schema(example = "{\n  \"erreur\": \"Utilisateur introuvable avec l'ID : 123e4567-e89b-12d3-a456-426614174000\"\n}")))
    })
    public ResponseEntity<UtilisateurReponse> modifierProfil(
            @PathVariable UUID id,
            @Valid @RequestBody MiseAJourUtilisateurRequete requete) {

        Utilisateur utilisateurMisAJour = utilisateurService.modifierProfil(id, requete.getNom(), requete.getPrenom());

        return ResponseEntity.ok(dtoMapper.toResponse(utilisateurMisAJour));
    }
}