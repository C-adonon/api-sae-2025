package future.SAE.application.service;

import future.SAE.domain.model.Cours;
import future.SAE.infrastructure.mapping.CoursMapper;
import future.SAE.infrastructure.persistence.CoursJPA;
import future.SAE.infrastructure.repository.CoursRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CoursService {

    private final CoursRepository coursRepository;
    private final CoursMapper coursMapper;

    public CoursService(CoursRepository repository, CoursMapper mapper){
        this.coursRepository = repository;
        this.coursMapper = mapper;
    }

    public Cours createCours(Cours cours){
        CoursJPA jpaToSave = coursMapper.toEntity(cours);
        CoursJPA jpaSaved = coursRepository.save(jpaToSave);
        return coursMapper.toDomain(jpaSaved);
    }

    public List<Cours> getAllCours() {
        List<CoursJPA> coursJPA = coursRepository.findAll();
        return coursMapper.toDomainList(coursJPA);
    }

    public Cours getCoursById(Long id){
        CoursJPA coursJPA = coursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ce cours avec l'id " + id + " n'existe pas."));
        return coursMapper.toDomain(coursJPA);
    }

    public List<Cours> getCoursByProfesseurId(UUID idProfesseur){
        List<CoursJPA> coursJPA = coursRepository.findByProfesseur_IdUser(idProfesseur);
        return coursMapper.toDomainList(coursJPA);
    }

    public List<Cours> getCoursByFormationId(Long idFormation){
        List<CoursJPA> coursJPA = coursRepository.findByFormation_Id(idFormation);
        return coursMapper.toDomainList(coursJPA);
    }

    public List<Cours> getPublicCours(){
        List<CoursJPA> coursJPA = coursRepository.findByPubliqueTrue();
        return coursMapper.toDomainList(coursJPA);
    }

    public List<Cours> getCoursByEleveId(UUID idEleve){
        List<CoursJPA> coursJPA = coursRepository.findByInscriptions_Eleve_IdUser(idEleve);
        return coursMapper.toDomainList(coursJPA);
    }

    public Cours updateCours(Long id, Cours newCours){
        CoursJPA coursExistant = coursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impossible de modifier : le cours avec l'ID " + id + " n'existe pas."));

        CoursJPA jpaToUpdate = coursMapper.toEntity(newCours);
        jpaToUpdate.setIdCours(id);
        CoursJPA jpaSaved = coursRepository.save(jpaToUpdate);
        return coursMapper.toDomain(jpaSaved);
    }

    public void deleteCours(Long id) {
        if (!coursRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : le cours " + id + " n'existe pas.");
        }
        coursRepository.deleteById(id);
    }
}