package future.SAE.domain.interfaces;

import future.SAE.domain.model.Formation;

import java.util.List;
import java.util.Optional;

public interface IFormationRepository
{
    Formation sauvegarder(Formation formation);
    Optional<Formation> trouverParId(Long id);
    List<Formation> trouverToutes();
    void supprimer(Formation formation);
}
