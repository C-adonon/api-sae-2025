package future.SAE.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.CompetenceJPA;

import java.util.List;

@Repository
public interface CompetenceRepository extends JpaRepository<CompetenceJPA, Long> {

    void removeByIdCompetence(Long idCompetence);
    List<CompetenceJPA> findByFormation_Id(Long idFormation);
}
