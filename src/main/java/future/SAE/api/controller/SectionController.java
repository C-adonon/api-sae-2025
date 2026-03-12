package future.SAE.api.controller;

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
    public ResponseEntity<List<SectionDTO>> getAllSections(){
        List<Section> sections = this.sectionService.getAllSections();
        List<SectionDTO> sectionsDTO = this.sectionDTOMapper.toDTOList(sections);
        return ResponseEntity.ok(sectionsDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SectionDTO> getSectionByid(@PathVariable Long id) {
        Section section = this.sectionService.getSectionById(id);
        SectionDTO dto = this.sectionDTOMapper.toDTO(section);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/cours/{idCours}")
    public ResponseEntity<List<SectionDTO>> getSectionByCours(@PathVariable Long idCours){
        List<Section> sections = this.sectionService.getSectionByCours_idCours(idCours);
        List<SectionDTO> sectionsDTO = this.sectionDTOMapper.toDTOList(sections);
        return ResponseEntity.ok(sectionsDTO);
    }

    @PostMapping
    public ResponseEntity<SectionDTO> createSection(@RequestBody SectionDTO sectionDTO){
        Section section = this.sectionDTOMapper.toDomain(sectionDTO);
        Section createdSection = this.sectionService.createSection(section);
        return ResponseEntity.status(201).body(this.sectionDTOMapper.toDTO(createdSection));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionDTO> updateSection(@PathVariable Long id,@RequestBody SectionDTO sectionDTO){
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
