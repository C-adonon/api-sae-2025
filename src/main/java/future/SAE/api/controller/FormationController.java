package future.SAE.api.controller;

import com.nimbusds.oauth2.sdk.Response;
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
    public ResponseEntity<List<FormationDTO>> getAllFormations(){
        List<Formation> formations = this.formationService.getAllFormations();
        return ResponseEntity.ok(this.formationDTOMapper.toDTOList(formations));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormationDTO> getFormationById(@PathVariable Long id){
        Formation formation = this.formationService.getFormationById(id);
        return ResponseEntity.ok(this.formationDTOMapper.toDTO(formation));
    }

    @PostMapping
    public ResponseEntity<FormationDTO> createFormation(@RequestBody FormationDTO formationDTO){
        Formation formation = this.formationDTOMapper.toDomain(formationDTO);
        Formation formationCreated = this.formationService.createFormation(formation);
        return ResponseEntity.status(201).body(this.formationDTOMapper.toDTO(formationCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormationDTO> updateFormation(@PathVariable Long id, @RequestBody FormationDTO formationDTO){
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
