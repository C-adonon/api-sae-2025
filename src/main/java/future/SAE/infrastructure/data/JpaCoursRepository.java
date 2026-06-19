package future.SAE.infrastructure.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.entity.CoursJPA;

@Repository
public interface JpaCoursRepository extends JpaRepository<CoursJPA, Long> {

    List<CoursJPA> findByProfesseur_Id(UUID idProfesseur);

    List<CoursJPA> findByFormation_Id(Long idFormation);

    List<CoursJPA> findByPubliqueTrue();
}
