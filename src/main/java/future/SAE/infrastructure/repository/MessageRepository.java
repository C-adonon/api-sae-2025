package future.SAE.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.MessageJPA;

@Repository
public interface MessageRepository extends JpaRepository<MessageJPA, UUID> {

    List<MessageJPA> findByExpediteurIdUserOrderByDateAsc(UUID idUser);

    List<MessageJPA> findByExpediteurIdUserOrderByDateDesc(UUID idUser);

    List<MessageJPA> findByDestinatairesIdUserOrderByDateAsc(UUID idUser);

    List<MessageJPA> findByDestinatairesIdUserOrderByDateDesc(UUID idUser);
}