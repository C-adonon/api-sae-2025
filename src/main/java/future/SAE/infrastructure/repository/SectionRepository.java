package future.SAE.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.SectionJPA;

@Repository
public interface SectionRepository extends JpaRepository<SectionJPA, Long> {
    List<SectionJPA> findByCoursIdCoursOrderByOrdreAsc(Long idCours);

    List<SectionJPA> findByCoursIdCours(Long idCours);
}
