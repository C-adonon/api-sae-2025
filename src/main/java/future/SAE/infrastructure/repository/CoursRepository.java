package future.SAE.infrastructure.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import future.SAE.infrastructure.persistence.CoursJPA;

@Repository
public interface CoursRepository extends JpaRepository<CoursJPA, Long> {
    List<CoursJPA> findByProfesseurIdUSer(UUID idUser);

    List<CoursJPA> findByFormationIdFormation(Long idFormation);

    List<CoursJPA> findByPubliqueTrue();

    List<CoursJPA> findByInscriptionEleveIdUser(UUID idUser);
}
