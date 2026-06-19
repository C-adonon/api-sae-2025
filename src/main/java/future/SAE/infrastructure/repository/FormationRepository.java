package future.SAE.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import future.SAE.domain.interfaces.IFormationRepository;
import future.SAE.domain.model.Formation;
import future.SAE.infrastructure.data.JpaFormationRepository;
import future.SAE.infrastructure.mapping.FormationMapper;
import future.SAE.infrastructure.persistence.entity.FormationJPA;

@Component
public class FormationRepository implements IFormationRepository {

    private final JpaFormationRepository jpaRepository;
    private final FormationMapper mapper;

    public FormationRepository(JpaFormationRepository jpaRepository, FormationMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Formation sauvegarder(Formation formation) {
        FormationJPA entity = mapper.toEntity(formation);
        FormationJPA savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Formation> trouverParId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Formation> trouverToutes() {
        return mapper.toDomainList(jpaRepository.findAll());
    }

    @Override
    public void supprimer(Formation formation) {
        jpaRepository.deleteById(formation.getIdFormation());
    }
}
