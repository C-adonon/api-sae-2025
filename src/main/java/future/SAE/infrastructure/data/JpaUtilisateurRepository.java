package future.SAE.infrastructure.data;

import future.SAE.infrastructure.persistence.entity.UtilisateurJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUtilisateurRepository extends JpaRepository<UtilisateurJPA, UUID> {

    Optional<UtilisateurJPA> findByIdentifiant(String identifiant);
    Optional<UtilisateurJPA> findByEmail(String email);
}