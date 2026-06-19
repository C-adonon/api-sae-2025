package future.SAE.infrastructure.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.entity.InscriptionCoursJPA;

@Repository
public interface JpaInscriptionCoursRepository extends JpaRepository<InscriptionCoursJPA, Long> {

    boolean existsByEleve_IdAndCours_IdCours(UUID eleveId, Long coursId);

    List<InscriptionCoursJPA> findByEleve_Id(UUID eleveId);

    List<InscriptionCoursJPA> findByCours_IdCours(Long coursId);
}
