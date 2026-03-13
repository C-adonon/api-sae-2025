package future.SAE.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import future.SAE.domain.model.Competence;
import future.SAE.application.service.CompetenceService;
import future.SAE.infrastructure.*;.mapping.CompetenceDTOMapper;
import jakarta.persistence.PostRemove;

@RestController
@RequestMapping("/api/competences")
public class CompetenceController {

    private final CompetenceService competenceService;
    private final CompetenceDTOMapper competenceDTOMapper;

    public ConmpetenceController(CompetenceService service, CompetenceDTOMapper mapper){
        this.competenceService = service;
        this.competenceDTOMapper = mapper;

    @GetMapping("")
    public ResponseEntity<List<CompetenceDTO>> getAllCompetences() {
        List<Competence> competences = competenceService.getAllCompetences();
        return ResponseEntity.ok(competenceDTOMapper.toDTOList(competences));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenceDTO> getCompetenceById(@PathVariable Long id) {
        Competence competence = competenceService.getCompetence;
        return ResponseEntity.ok(competenceDTOMapper.toDTO(competence));
    }

    @PostMapping("")
    public ResponseEntity<CompetenceDTO> createCompetence(@RequestBody CompetenceDTO dto) {
        Competence nouvelleComp = competenceService.createCompetence(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(competenceDTOMapper.toDTO(nouvelleComp));

    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetenceDTO> updateCompetence(@PathVariable long id, @RequestBody competenceDTO dto) {
        Competence miseAJour = competenceService.updateCompetence(id, dto);
        return ResponseEntity.ok(competenceDTOMapper.toDTO(miseAJour));
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<void> deleteCompetence(@PathVariable Long id){
            competenceService.deleteCompetence(id);
            return ResponseEntity.noContent().build();
        }

}

}
