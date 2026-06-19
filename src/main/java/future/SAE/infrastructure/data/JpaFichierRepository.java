package future.SAE.infrastructure.data;


import future.SAE.infrastructure.persistence.entity.FichierJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFichierRepository extends JpaRepository<FichierJPA, Long> {

}