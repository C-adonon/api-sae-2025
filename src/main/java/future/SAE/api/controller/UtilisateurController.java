package future.SAE.api.controller;

import future.SAE.api.dto.reponse.UtilisateurReponse;
import future.SAE.api.dto.requete.MiseAJourUtilisateurRequete;
import future.SAE.api.mapping.UtilisateurDTOMapper;
import future.SAE.application.interfaces.IUtilisateurService;
import future.SAE.domain.model.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final IUtilisateurService utilisateurService;
    private final UtilisateurDTOMapper dtoMapper;

    public UtilisateurController(IUtilisateurService utilisateurService, UtilisateurDTOMapper dtoMapper) {
        this.utilisateurService = utilisateurService;
        this.dtoMapper = dtoMapper;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurReponse> consulterProfil(@PathVariable UUID id) {
        Utilisateur utilisateur = utilisateurService.consulterProfil(id);

        return ResponseEntity.ok(dtoMapper.toResponse(utilisateur));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurReponse> modifierProfil(
            @PathVariable UUID id,
            @Valid @RequestBody MiseAJourUtilisateurRequete requete) {

        Utilisateur utilisateurMisAJour = utilisateurService.modifierProfil(id, requete.getNom(), requete.getPrenom());

        return ResponseEntity.ok(dtoMapper.toResponse(utilisateurMisAJour));
    }
}