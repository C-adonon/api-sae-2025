package future.SAE.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.InscriptionCoursJPA;

@Repository
public interface InscriptionCoursRepository extends JpaRepository<InscriptionCoursJPA, Long> {
    List<InscriptionCoursJPA> findByEleveIdUserAndCoursIdCours(UUID IdUser, Long idCours);

    InscriptionCoursJPA findByCoursIdCoursAndEleveIdUser(Long idCours, UUID idUser);

    boolean existsByCoursIdCoursAndEleveIdUser(Long idCours, UUID idUser);

    int countByCoursIdCours(Long idCours);

    int countByEleveIdUser(UUID idUser);

}
