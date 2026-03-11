package future.SAE.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.CompetenceJPA;

@Repository
public interface CompetenceRepository extends JpaRepository<CompetenceJPA, Long> {

}
