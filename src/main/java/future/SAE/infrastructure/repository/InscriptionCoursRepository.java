package future.SAE.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import future.SAE.domain.interfaces.IInscriptionCoursRepository;
import future.SAE.domain.model.InscriptionCours;
import future.SAE.infrastructure.data.JpaInscriptionCoursRepository;
import future.SAE.infrastructure.mapping.InscriptionCoursMapper;
import future.SAE.infrastructure.persistence.entity.InscriptionCoursJPA;

@Component
public class InscriptionCoursRepository implements IInscriptionCoursRepository {

    private final JpaInscriptionCoursRepository jpaRepository;
    private final InscriptionCoursMapper mapper;

    public InscriptionCoursRepository(JpaInscriptionCoursRepository jpaRepository, InscriptionCoursMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public InscriptionCours sauvegarder(InscriptionCours inscriptionCours) {
        InscriptionCoursJPA entity = mapper.toEntity(inscriptionCours);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<InscriptionCours> trouverParId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<InscriptionCours> trouverTous() {
        return mapper.toDomainList(jpaRepository.findAll());
    }

    @Override
    public void supprimer(InscriptionCours inscriptionCours) {
        jpaRepository.deleteById(inscriptionCours.getIdInscriptionCours());
    }

    @Override
    public boolean estInscrit(UUID eleveId, Long coursId) {
        return jpaRepository.existsByEleve_IdAndCours_IdCours(eleveId, coursId);
    }

    @Override
    public List<InscriptionCours> trouverParEleve(UUID eleveId) {
        return mapper.toDomainList(jpaRepository.findByEleve_Id(eleveId));
    }

    @Override
    public List<InscriptionCours> trouverParCours(Long coursId) {
        return mapper.toDomainList(jpaRepository.findByCours_IdCours(coursId));
    }
}
