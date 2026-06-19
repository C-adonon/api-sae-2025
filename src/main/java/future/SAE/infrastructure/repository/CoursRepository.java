package future.SAE.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import future.SAE.domain.interfaces.ICoursRepository;
import future.SAE.domain.model.Cours;
import future.SAE.infrastructure.data.JpaCoursRepository;
import future.SAE.infrastructure.mapping.CoursMapper;
import future.SAE.infrastructure.persistence.entity.CoursJPA;

@Component
public class CoursRepository implements ICoursRepository {

    private final JpaCoursRepository jpaRepository;
    private final CoursMapper mapper;

    public CoursRepository(JpaCoursRepository jpaRepository, CoursMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Cours> trouverParUtilisateurId(UUID utilisateurId) {
        return mapper.toDomainList(jpaRepository.findByProfesseur_Id(utilisateurId));
    }

    @Override
    public Cours sauvegarder(Cours cours) {
        CoursJPA entity = mapper.toEntity(cours);
        CoursJPA savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Cours> trouverParId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Cours> trouverTous() {
        return mapper.toDomainList(jpaRepository.findAll());
    }

    @Override
    public void supprimer(Cours cours) {
        jpaRepository.deleteById(cours.getIdCours());
    }
}
