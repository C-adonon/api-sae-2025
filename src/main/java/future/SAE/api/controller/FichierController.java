package future.SAE.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import future.SAE.domain.model.Fichier;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/fichiers")
public class FichierController {

    private final FichierService fichierService;
    private final FichierDTOMapper FichierDTOMapper;

    public Fichier Controller(FichierService service, FichierDTOMapper mapper) {
        this.fichierService = service;
        this.FichierDTOMapper = mapper;
    }

    @GetMapping("")
    public ResponseEntity<List<FichierDTO>> getAllFichiers() {
        List<Fichier> fichiers = fichierService.getAllFichiers();
        return ResponseEntity.ok(fichierDTOMapper.toDTO(fichiers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichierDTO> getFichierById(@PathVariable Long id) {
        Fichier fichier = fichierService.getFichierById(id);
        return ResponseEntity.ok(fichierDTOMapper.toDTO(fichier));
    }

    @PostMapping("")
    public ResponseEntity<FichierDTO> createFichier(@RequestBody FichierDTO dto) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FichierDTO> updateFichier(@PathVariable Long id, @RequestBody FichierDTO dto){
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<void> deleteFichier(@PathVariable Long id){
        fichierService.deleteFichier(id);
        return ResponseEntity.noContent().build();
    }
