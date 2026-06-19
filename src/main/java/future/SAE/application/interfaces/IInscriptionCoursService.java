package future.SAE.application.interfaces;

import java.util.List;

import future.SAE.domain.model.InscriptionCours;

public interface IInscriptionCoursService {

    /** Inscrit l'élève actuellement connecté au cours donné. */
    InscriptionCours inscrireCoursCourant(Long coursId);

    /** Désinscrit (supprime une inscription) ; réservé à l'élève concerné ou à un professeur. */
    void desinscrire(Long inscriptionId);

    /** Liste les inscriptions de l'élève connecté. */
    List<InscriptionCours> listerMesInscriptions();

    /** Liste les inscriptions d'un cours (vue professeur). */
    List<InscriptionCours> listerInscriptionsParCours(Long coursId);
}
