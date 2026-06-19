package future.SAE.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import future.SAE.api.dto.FormationReponseDTO;
import future.SAE.api.dto.FormationRequeteDTO;
import future.SAE.api.mapping.FormationDTOMapper;
import future.SAE.application.interfaces.IFormationService;
import future.SAE.domain.model.Formation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/formations")
@Tag(name = "Formations", description = "API de gestion des formations")
public class FormationController {

    private final IFormationService formationService;
    private final FormationDTOMapper dtoMapper;

    public FormationController(IFormationService formationService, FormationDTOMapper dtoMapper) {
        this.formationService = formationService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    @Operation(summary = "Lister les formations", description = "Récupère la liste de toutes les formations.")
    public ResponseEntity<List<FormationReponseDTO>> listerFormations() {
        List<Formation> formations = formationService.listerFormation();
        return ResponseEntity.ok(dtoMapper.toDTOList(formations));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une formation", description = "Récupère une formation via son identifiant.")
    public ResponseEntity<FormationReponseDTO> accederFormation(@PathVariable Long id) {
        Formation formation = formationService.accederFormation(id);
        return ResponseEntity.ok(dtoMapper.toDTO(formation));
    }

    @PostMapping
    @Operation(summary = "Créer une formation", description = "Crée une nouvelle formation (année et nom).")
    public ResponseEntity<FormationReponseDTO> creerFormation(@RequestBody FormationRequeteDTO requete) {
        Formation formation = formationService.creerFormation(requete.getAnnee(), requete.getNom());
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toDTO(formation));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une formation", description = "Met à jour le nom d'une formation existante.")
    public ResponseEntity<FormationReponseDTO> modifierFormation(@PathVariable Long id, @RequestBody FormationRequeteDTO requete) {
        Formation formation = formationService.modifierFormation(id, requete.getNom());
        return ResponseEntity.ok(dtoMapper.toDTO(formation));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une formation", description = "Supprime une formation via son identifiant.")
    public ResponseEntity<Void> supprimerFormation(@PathVariable Long id) {
        formationService.supprimerFormation(id);
        return ResponseEntity.noContent().build();
    }
}
