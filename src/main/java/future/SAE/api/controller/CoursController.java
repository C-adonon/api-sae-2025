package future.SAE.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import future.SAE.api.dto.CoursReponseDTO;
import future.SAE.api.dto.CoursRequeteDTO;
import future.SAE.api.mapping.CoursDTOMapper;
import future.SAE.application.interfaces.ICoursService;
import future.SAE.domain.model.Cours;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cours")
@Tag(name = "Cours", description = "API de gestion des cours")
public class CoursController {

    private final ICoursService coursService;
    private final CoursDTOMapper dtoMapper;

    public CoursController(ICoursService coursService, CoursDTOMapper dtoMapper) {
        this.coursService = coursService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    @Operation(summary = "Lister les cours", description = "Récupère la liste de tous les cours.")
    public ResponseEntity<List<CoursReponseDTO>> listerCours() {
        List<Cours> cours = coursService.listerCours();
        return ResponseEntity.ok(dtoMapper.toDTOList(cours));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un cours", description = "Récupère un cours via son identifiant.")
    public ResponseEntity<CoursReponseDTO> accederCours(@PathVariable Long id) {
        Cours cours = coursService.accederCours(id);
        return ResponseEntity.ok(dtoMapper.toDTO(cours));
    }

    @PostMapping
    @Operation(summary = "Créer un cours", description = "Crée un nouveau cours à partir de son nom.")
    public ResponseEntity<CoursReponseDTO> creerCours(@RequestBody CoursRequeteDTO requete) {
        Cours cours = coursService.creerCours(requete.getNom());
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toDTO(cours));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un cours", description = "Met à jour le nom d'un cours existant.")
    public ResponseEntity<CoursReponseDTO> modifierCours(@PathVariable Long id, @RequestBody CoursRequeteDTO requete) {
        Cours cours = coursService.modifierCours(id, requete.getNom());
        return ResponseEntity.ok(dtoMapper.toDTO(cours));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un cours", description = "Supprime un cours via son identifiant.")
    public ResponseEntity<Void> supprimerCours(@PathVariable Long id) {
        coursService.supprimerCours(id);
        return ResponseEntity.noContent().build();
    }
}
