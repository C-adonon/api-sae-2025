package future.SAE.application.services;

import future.SAE.application.exception.FormationIntrouvableException;
import future.SAE.application.interfaces.IFormationService;
import future.SAE.domain.interfaces.IFormationRepository;
import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Formation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormationService implements IFormationService
{
    private final IFormationRepository formationRepository;

    public FormationService(IFormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

    @Override
    public Formation creerFormation(int annee, String nom)
    {
        Formation f = new Formation();
        f.setAnnee(annee);
        f.setNom(nom);
        return formationRepository.sauvegarder(f);
    }

    @Override
    public Formation modifierFormation(Long id, String nouveauNom)
    {
        Formation f = formationRepository.trouverParId(id).orElseThrow(FormationIntrouvableException::new);
        f.setNom(nouveauNom);
        return formationRepository.sauvegarder(f);
    }

    @Override
    public Formation accederFormation(Long id)
    {
        return formationRepository.trouverParId(id).orElseThrow(FormationIntrouvableException::new);
    }

    @Override
    public List<Formation> listerFormation()
    {
        return formationRepository.trouverToutes();
    }

    @Override
    public void supprimerFormation(Long id)
    {
        Formation f = formationRepository.trouverParId(id).orElseThrow(FormationIntrouvableException::new);
        formationRepository.supprimer(f);
    }

    @Override
    public void ajouterCours(Long formationId, Cours cours)
    {
        Formation f = formationRepository.trouverParId(formationId).orElseThrow(FormationIntrouvableException::new);
        f.ajouterCours(cours);
        formationRepository.sauvegarder(f);
    }

    @Override
    public void supprimerCours(Long formationId, Cours cours)
    {
        Formation f = formationRepository.trouverParId(formationId).orElseThrow(FormationIntrouvableException::new);
        f.supprimerCours(cours);
        formationRepository.sauvegarder(f);
    }
}
