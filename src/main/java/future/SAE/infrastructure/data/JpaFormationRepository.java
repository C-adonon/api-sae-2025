package future.SAE.infrastructure.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.entity.FormationJPA;

@Repository
public interface JpaFormationRepository extends JpaRepository<FormationJPA, Long> {
}
