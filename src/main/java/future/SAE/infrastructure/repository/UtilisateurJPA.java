package future.SAE.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.UtilisateurJPA;

@Repository
public class UtilisateurJPA extends JpaRepository<UtilisateurJPA, UUID> {
}
