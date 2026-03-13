package future.SAE.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.EtatSectionJPA;

@Repository
public interface EtatSectionRepository extends JpaRepository<EtatSectionJPA, Long> {
    List<EtatSectionJPA> findByEleve_IdUser(UUID eleveIdUser);

    List<EtatSectionJPA> findByEleve_IdUserAndEstTermineeTrue(UUID idUser);


}
