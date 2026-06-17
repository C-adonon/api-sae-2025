package future.SAE.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.entity.SuiviCoursJPA;

@Repository
public interface SuiviCoursRepository extends JpaRepository<SuiviCoursJPA, Long> {

}

