package future.SAE.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.FichierJPA;

public interface FichierRepository extends JpaRepository<FichierJPA, Long> {

    List<FichierJPA> findBySectionIdSection(Long idSection);
}