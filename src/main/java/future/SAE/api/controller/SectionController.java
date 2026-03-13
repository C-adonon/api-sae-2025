package future.SAE.api.controller;

import future.SAE.api.dto.SectionReponseDTO;
import future.SAE.api.dto.SectionRequeteDTO;
import future.SAE.application.mapping.SectionDTOMapper;
import future.SAE.application.service.SectionService;
import future.SAE.domain.model.Section;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionController {

    private final SectionService sectionService;
    private final SectionDTOMapper sectionDTOMapper;

    public SectionController(SectionService service, SectionDTOMapper mapper){
        this.sectionService = service;
        this.sectionDTOMapper = mapper;
    }


    @GetMapping("/")
    public ResponseEntity<List<SectionReponseDTO>> getAllSections(){
        List<Section> sections = this.sectionService.getAllSections();
        List<SectionReponseDTO> sectionsDTO = this.sectionDTOMapper.toDTOList(sections);
        return ResponseEntity.ok(sectionsDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SectionReponseDTO> getSectionByid(@PathVariable Long id) {
        Section section = this.sectionService.getSectionById(id);
        SectionReponseDTO dto = this.sectionDTOMapper.toDTO(section);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/cours/{idCours}")
    public ResponseEntity<List<SectionReponseDTO>> getSectionByCours(@PathVariable Long idCours){
        List<Section> sections = this.sectionService.getSectionsByCoursId(idCours);
        List<SectionReponseDTO> sectionsDTO = this.sectionDTOMapper.toDTOList(sections);
        return ResponseEntity.ok(sectionsDTO);
    }

    @PostMapping
    public ResponseEntity<SectionReponseDTO> createSection(@RequestBody SectionRequeteDTO sectionDTO){
        Section section = this.sectionDTOMapper.toDomain(sectionDTO);
        Section createdSection = this.sectionService.createSection(section);
        return ResponseEntity.status(201).body(this.sectionDTOMapper.toDTO(createdSection));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionReponseDTO> updateSection(@PathVariable Long id,@RequestBody SectionRequeteDTO sectionDTO){
        Section newSection = this.sectionDTOMapper.toDomain(sectionDTO);
        Section sectionMaj = sectionService.updateSection(id, newSection);
        return ResponseEntity.ok(this.sectionDTOMapper.toDTO(sectionMaj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Long id){
        this.sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }
}
