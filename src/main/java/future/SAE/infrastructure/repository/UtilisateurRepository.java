package future.SAE.infrastructure.repository;

import future.SAE.domain.model.Utilisateur;
import future.SAE.domain.interfaces.IUtilisateurRepository;
import future.SAE.infrastructure.data.JpaUtilisateurRepository;
import future.SAE.infrastructure.persistence.entity.UtilisateurJPA;
import future.SAE.infrastructure.mapping.UtilisateurMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UtilisateurRepository implements IUtilisateurRepository {

    private final JpaUtilisateurRepository jpaRepository;
    private final UtilisateurMapper mapper;

    // Injection via Spring
    public UtilisateurRepository(JpaUtilisateurRepository jpaRepository, UtilisateurMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Utilisateur> trouverParIdentifiant(String identifiant) {
        return jpaRepository.findByIdentifiant(identifiant).map(mapper::toDomain);
    }

    @Override
    public Optional<Utilisateur> trouverParId(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Utilisateur> trouverParEmail(String email) {
        return jpaRepository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Utilisateur sauvegarder(Utilisateur utilisateur) {
        UtilisateurJPA entity = mapper.toEntity(utilisateur);
        UtilisateurJPA savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}