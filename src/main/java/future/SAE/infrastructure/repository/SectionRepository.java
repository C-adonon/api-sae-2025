package future.SAE.infrastructure.repository;

import future.SAE.domain.interfaces.ISectionRepository; // Assure-toi que cette interface existe dans ton domaine !
import future.SAE.domain.model.Section;
import future.SAE.infrastructure.data.JpaSectionRepository;
import future.SAE.infrastructure.persistence.entity.SectionJPA;
import future.SAE.infrastructure.mapping.SectionMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SectionRepository implements ISectionRepository {

    private final future.SAE.infrastructure.data.JpaSectionRepository jpaRepository;
    private final SectionMapper mapper;

    public SectionRepository(JpaSectionRepository jpaRepository, SectionMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Section sauvegarder(Section section) {
        SectionJPA entity = mapper.toEntity(section);
        SectionJPA savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Section> trouverParId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Section> trouverToutes() {
        return mapper.toDomainList(jpaRepository.findAll());
    }

    @Override
    public void supprimer(Section section) {
        jpaRepository.deleteById(section.getIdSection());
    }
}