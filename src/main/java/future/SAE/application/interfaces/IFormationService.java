package future.SAE.application.interfaces;

import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Formation;

import java.util.List;

public interface IFormationService
{
    Formation creerFormation(int annee, String nom);
    Formation modifierFormation(Long id, String nouveauNom);
    Formation accederFormation(Long id);
    List<Formation> listerFormation();
    void supprimerFormation(Long id);
    void ajouterCours(Long formationId, Cours cours);
    void supprimerCours(Long formationId, Cours cours);
}
