package future.SAE.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import future.SAE.infrastructure.persistence.EleveJPA;
import future.SAE.infrastructure.persistence.UtilisateurJPA;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurJPA, UUID>
{
    Optional<UtilisateurJPA> findByEmail(String email);
    Optional<UtilisateurJPA> findByIdentifiant(int identifiant);

    //Les eleves d'une formation
    List<EleveJPA> findAllElevesByFormation_Id(Long idFormation);

    //Les eleves d'un cours dans l'ordre alphabetique
    @Query("SELECT i.eleve FROM InscriptionCoursJPA i WHERE i.cours.idCours = :idCours ORDER BY i.eleve.nom ASC")
    List<EleveJPA> findElevesInscritsAuCours(@Param("idCours") Long idCours);

    //Les eleves ayant fini le cours dans l'ordre alphabetique
    @Query("SELECT suivi.eleve FROM SuiviCoursJPA suivi WHERE suivi.cours.idCours = :idCours AND suivi.progressionGlobale = 100")
    List<EleveJPA> findElevesCoursTermine(@Param("idCours") Long idCours);

    //Les eleves n'ayant pas fini le cours dans l'ordre alphabetique
    @Query("SELECT suivi.eleve FROM SuiviCoursJPA suivi WHERE suivi.cours.idCours = :idCours AND suivi.progressionGlobale < 100")
    List<EleveJPA> findElevesCoursNonTermine(@Param("idCours") Long idCours);

    //verifie si un professeur existe par son mail et son role
    boolean existsByEmailAndRole(String email, String role);
}
