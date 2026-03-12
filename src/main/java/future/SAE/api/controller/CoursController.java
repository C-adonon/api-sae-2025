package future.SAE.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import future.SAE.api.dto.coursReponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class CoursController
{
    private final CoursService coursService;
    private final CoursDTOMapper coursDTOMapper;

    public CoursController(CoursService service, CoursDTOMapper mapper)
    {
        this.coursService = service;
        this.coursDTOMapper = mapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<CoursReponseDTO>> getAllCours()
    {
        List<Cours> cours = this.coursService.getAllCours();
        List<CoursReponseDTO> coursDto = this.coursDTOMapper.toDTOList(cours);
        return ResponseEntity.ok(coursService.getAllCours());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoursReponseDTO> getCoursById(@PathVariable Long id)
    {
        Cours cours = coursService.getCoursById(id);
        CoursReponseDTO dto = coursDTOMapper.toDTO(cours);
        if (id == null || id < 0)
        {
            return ResponseEntity.badRequest().build();
        }
        else
        {
            return coursService.getCoursById(id).map(cours -> ResponseEntity.ok(cours)).orElseGet(() -> ResponseEntity.notFound().build());

        }
    }

    @PostMapping("/")
    public ResponseEntity<CoursReponseDTO> createCours(@Valid @RequestBody CoursRequeteDTO coursRequete) {
        Cours cours = coursService.createCours(coursRequete);
        CoursReponseDTO dto = coursDTOMapper.toDTO(cours);
        if (coursRequete == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(coursService.createCours(coursRequete));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<CoursReponseDTO> updateCours(@PathVariable Long id, @Valid @RequestBody CoursRequeteDTO coursRequete) {
        Cours cours = coursService.updateCours(id, coursRequete);
        CoursReponseDTO dto = coursDTOMapper.toDTO(cours);
        if (id == null || id < 0) {
            return ResponseEntity.badRequest().build();
        } else {
            return coursService.updateCours(id, coursRequete).map(cours -> ResponseEntity.ok(cours)).orElseGet(() -> ResponseEntity.notFound().build());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CoursReponseDTO> deleteCours(@PathVariable Long id)
    {
        Cours cours = coursService.deleteCours(id);
        CoursReponseDTO dto = coursDTOMapper.toDTO(cours);
        if (id == null || id < 0) {
            return ResponseEntity.badRequest().build();
        }
        else
        {
            return coursService.deleteCours(id).map(reponse -> ResponseEntity.notContent().build).orElseGet(() -> ResponseEntity.notFound().build());
        }
    }

}
