package future.SAE.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.FormationJPA;

@Repository
public interface FormationRepository extends JpaRepository<FormationJPA, Long> {

    FormationJPA findByResponsableIdUser(UUID idUser);

}
