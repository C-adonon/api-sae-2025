package future.SAE.application.services;

import future.SAE.application.exception.FichierIntrouvableException;
import future.SAE.application.interfaces.IFichierService;
import future.SAE.domain.interfaces.IFichierRepository;
import future.SAE.domain.model.Fichier;

import java.util.List;

public class FichierService implements IFichierService
{
    private final IFichierRepository fichierRepository;

    public FichierService(IFichierRepository fichierRepository) {
        this.fichierRepository = fichierRepository;
    }

    @Override
    public Fichier creerFichier(String titre, String description, String cheminFichier)
    {
         Fichier f = new Fichier();
         f.setTitre(titre);
         f.setDescription(description);
         f.setCheminFichier(cheminFichier);

         return fichierRepository.sauvegarder(f);
    }

    @Override
    public Fichier modifierFichier(Long id, String titre, String description) {
        Fichier f = fichierRepository.trouverParId(id).orElseThrow(FichierIntrouvableException::new);
        f.setTitre(titre);
        f.setDescription(description);

        return fichierRepository.sauvegarder(f);
    }

    @Override
    public Fichier accederFichier(Long id)
    {
        return fichierRepository.trouverParId(id).orElseThrow(FichierIntrouvableException::new);
    }

    @Override
    public List<Fichier> listerFichier()
    {
        return fichierRepository.trouverTous();
    }

    @Override
    public void supprimerFichier(Long id)
    {
        Fichier f = fichierRepository.trouverParId(id).orElseThrow(FichierIntrouvableException::new);
        fichierRepository.supprimer(f);
    }
}
