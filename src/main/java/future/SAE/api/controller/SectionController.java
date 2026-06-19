package future.SAE.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import future.SAE.api.dto.SectionReponseDTO;
import future.SAE.api.dto.SectionRequeteDTO;
import future.SAE.api.mapping.SectionDTOMapper;
import future.SAE.application.interfaces.ISectionService;
import future.SAE.domain.model.Section;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/sections")
@Tag(name = "Sections", description = "API de gestion des sections d'un cours")
public class SectionController {

    private final ISectionService sectionService;
    private final SectionDTOMapper dtoMapper;

    public SectionController(ISectionService sectionService, SectionDTOMapper dtoMapper) {
        this.sectionService = sectionService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    @Operation(summary = "Lister les sections", description = "Récupère la liste de toutes les sections.")
    public ResponseEntity<List<SectionReponseDTO>> listerSections() {
        List<Section> sections = sectionService.listerSection();
        return ResponseEntity.ok(dtoMapper.toDTOList(sections));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une section", description = "Récupère une section via son identifiant.")
    public ResponseEntity<SectionReponseDTO> accederSection(@PathVariable Long id) {
        Section section = sectionService.accederSection(id);
        return ResponseEntity.ok(dtoMapper.toDTO(section));
    }

    @PostMapping
    @Operation(summary = "Créer une section", description = "Crée une nouvelle section rattachée à un cours.")
    public ResponseEntity<SectionReponseDTO> creerSection(@RequestBody SectionRequeteDTO requete) {
        Section section = sectionService.creerSection(
                requete.getOrdre(), requete.getTitre(), requete.getTexte(), (long) requete.getCoursId());
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toDTO(section));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une section", description = "Met à jour le titre et le texte d'une section.")
    public ResponseEntity<SectionReponseDTO> modifierSection(@PathVariable Long id, @RequestBody SectionRequeteDTO requete) {
        Section section = sectionService.modifierSection(id, requete.getTitre(), requete.getTexte());
        return ResponseEntity.ok(dtoMapper.toDTO(section));
    }

    @PostMapping("/{id}/ouvrir")
    @Operation(summary = "Ouvrir une section", description = "Rend une section accessible aux élèves.")
    public ResponseEntity<Void> ouvrirSection(@PathVariable Long id) {
        sectionService.ouvrirSection(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/fermer")
    @Operation(summary = "Fermer une section", description = "Rend une section inaccessible aux élèves.")
    public ResponseEntity<Void> fermerSection(@PathVariable Long id) {
        sectionService.fermerSection(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une section", description = "Supprime une section via son identifiant.")
    public ResponseEntity<Void> supprimerSection(@PathVariable Long id) {
        sectionService.supprimerSection(id);
        return ResponseEntity.noContent().build();
    }
}
