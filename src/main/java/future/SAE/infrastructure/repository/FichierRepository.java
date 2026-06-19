package future.SAE.infrastructure.repository;

import future.SAE.domain.interfaces.IFichierRepository;
import future.SAE.domain.model.Fichier;
import future.SAE.infrastructure.data.JpaFichierRepository;
import future.SAE.infrastructure.persistence.entity.FichierJPA;
import future.SAE.infrastructure.mapping.FichierMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FichierRepository implements IFichierRepository {

    private final JpaFichierRepository jpaRepository;
    private final FichierMapper mapper;

    public FichierRepository(JpaFichierRepository jpaRepository, FichierMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Fichier sauvegarder(Fichier fichier) {
        FichierJPA entity = mapper.toEntity(fichier);
        FichierJPA savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Fichier> trouverParId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Fichier> trouverTous() {
        return mapper.toDomainList(jpaRepository.findAll());
    }

    @Override
    public void supprimer(Fichier fichier) {
        jpaRepository.deleteById(fichier.getId());
    }
}