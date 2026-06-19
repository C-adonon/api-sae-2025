package future.SAE.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import future.SAE.api.dto.FichierReponseDTO;
import future.SAE.api.dto.FichierRequeteDTO;
import future.SAE.api.mapping.FichierDTOMapper;
import future.SAE.application.interfaces.IFichierService;
import future.SAE.application.interfaces.ISectionService;
import future.SAE.domain.model.Fichier;
import future.SAE.domain.model.Section;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/fichiers")
@Tag(name = "Fichiers", description = "API de gestion des fichiers d'une section")
public class FichierController {

    private final IFichierService fichierService;
    private final ISectionService sectionService;
    private final FichierDTOMapper dtoMapper;

    public FichierController(IFichierService fichierService, ISectionService sectionService, FichierDTOMapper dtoMapper) {
        this.fichierService = fichierService;
        this.sectionService = sectionService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    @Operation(summary = "Lister les fichiers", description = "Récupère la liste de tous les fichiers.")
    public ResponseEntity<List<FichierReponseDTO>> listerFichiers() {
        List<Fichier> fichiers = fichierService.listerFichier();
        return ResponseEntity.ok(dtoMapper.toDTOList(fichiers));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un fichier", description = "Récupère un fichier via son identifiant.")
    public ResponseEntity<FichierReponseDTO> accederFichier(@PathVariable Long id) {
        Fichier fichier = fichierService.accederFichier(id);
        return ResponseEntity.ok(dtoMapper.toDTO(fichier));
    }

    @PostMapping
    @Operation(summary = "Créer un fichier", description = "Ajoute un fichier à une section existante.")
    public ResponseEntity<FichierReponseDTO> creerFichier(@RequestBody FichierRequeteDTO requete) {
        Section section = sectionService.accederSection(requete.getSectionId());
        Fichier fichier = fichierService.creerFichier(
                requete.getTitre(), requete.getDescription(), requete.getCheminFichier(), section, requete.getType());
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toDTO(fichier));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un fichier", description = "Met à jour le titre et la description d'un fichier.")
    public ResponseEntity<FichierReponseDTO> modifierFichier(@PathVariable Long id, @RequestBody FichierRequeteDTO requete) {
        Fichier fichier = fichierService.modifierFichier(id, requete.getTitre(), requete.getDescription());
        return ResponseEntity.ok(dtoMapper.toDTO(fichier));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un fichier", description = "Supprime un fichier via son identifiant.")
    public ResponseEntity<Void> supprimerFichier(@PathVariable Long id) {
        fichierService.supprimerFichier(id);
        return ResponseEntity.noContent().build();
    }
}
