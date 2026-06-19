package future.SAE.infrastructure.data;

import future.SAE.infrastructure.persistence.entity.SectionJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSectionRepository extends JpaRepository<SectionJPA, Long> {
}