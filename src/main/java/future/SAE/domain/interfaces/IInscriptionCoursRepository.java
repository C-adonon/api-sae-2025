package future.SAE.domain.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import future.SAE.domain.model.InscriptionCours;

public interface IInscriptionCoursRepository {
    InscriptionCours sauvegarder(InscriptionCours inscriptionCours);
    Optional<InscriptionCours> trouverParId(Long id);
    List<InscriptionCours> trouverTous();
    void supprimer(InscriptionCours inscriptionCours);

    boolean estInscrit(UUID eleveId, Long coursId);
    List<InscriptionCours> trouverParEleve(UUID eleveId);
    List<InscriptionCours> trouverParCours(Long coursId);
}
