package future.SAE.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.EtatSectionJPA;

@Repository
public interface EtatSectionRepository extends JpaRepository<EtatSectionJPA, Long> {
    Optional<EtatSectionJPA> findByEleveIdUserAndSectionIdSection(UUID idUser, Long idSection);

    // Trouver tous les EtatSection d'un élève pour une section donnée terminée
    List<EtatSectionJPA> finByEleveIdUserAndSectionIdSectionAndEstTermineeTrue(UUID idUser, Long idSection);

}
