package future.SAE.api.controller;

import future.SAE.api.dto.FormationReponseDTO;
import future.SAE.api.dto.FormationRequeteDTO;
import future.SAE.application.mapping.FormationDTOMapper;
import future.SAE.application.service.FormationService;
import future.SAE.domain.model.Formation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formations")
public class FormationController {

    private final FormationService formationService;
    private final FormationDTOMapper formationDTOMapper;

    public FormationController(FormationService service, FormationDTOMapper formationDTOMapper){
        this.formationService = service;
        this.formationDTOMapper = formationDTOMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<FormationReponseDTO>> getAllFormations(){
        List<Formation> formations = this.formationService.getAllFormations();
        return ResponseEntity.ok(this.formationDTOMapper.toDTOList(formations));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormationReponseDTO> getFormationById(@PathVariable Long id){
        Formation formation = this.formationService.getFormationById(id);
        return ResponseEntity.ok(this.formationDTOMapper.toDTO(formation));
    }

    @PostMapping
    public ResponseEntity<FormationReponseDTO> createFormation(@RequestBody FormationRequeteDTO formationDTO){
        Formation formation = this.formationDTOMapper.toDomain(formationDTO);
        Formation formationCreated = this.formationService.createFormation(formation);
        return ResponseEntity.status(201).body(this.formationDTOMapper.toDTO(formationCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormationReponseDTO> updateFormation(@PathVariable Long id, @RequestBody FormationRequeteDTO formationDTO){
        Formation newFormation = this.formationDTOMapper.toDomain(formationDTO);
        Formation formationUpdated = this.formationService.updateFormation(id, newFormation);
        return ResponseEntity.ok(this.formationDTOMapper.toDTO(formationUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id){
        this.formationService.deleteFormation(id);
        return ResponseEntity.noContent().build();
    }
}
