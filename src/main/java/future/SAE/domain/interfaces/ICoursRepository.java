package future.SAE.domain.interfaces;

import future.SAE.domain.model.Cours;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICoursRepository
{
    List<Cours> trouverParUtilisateurId(UUID utilisateurId);
    Cours sauvegarder(Cours cours);
    Optional<Cours> trouverParId(Long id);
    List<Cours> trouverTous();
    void supprimer(Cours cours);
}
