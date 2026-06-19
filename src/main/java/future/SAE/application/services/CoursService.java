package future.SAE.application.services;

import future.SAE.application.interfaces.ICoursService;
import future.SAE.domain.interfaces.ICoursRepository;
import future.SAE.domain.model.Cours;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursService implements ICoursService
{
    private final ICoursRepository coursRepository;

    public CoursService(ICoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    @Override
    public Cours creerCours(String nom)
    {
        Cours c = new Cours(nom, null, null);
        return coursRepository.sauvegarder(c);
    }

    @Override
    public Cours modifierCours(Long id, String nouveauNom)
    {
        Cours c = coursRepository.trouverParId(id).orElseThrow(RuntimeException::new);
        c.setNom(nouveauNom);
        return coursRepository.sauvegarder(c);
    }

    @Override
    public void supprimerCours(Long id)
    {
        Cours c = coursRepository.trouverParId(id).orElseThrow(RuntimeException::new);
        coursRepository.supprimer(c);
    }

    @Override
    public Cours accederCours(Long id)
    {
        return coursRepository.trouverParId(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Cours> listerCours()
    {
        return coursRepository.trouverTous();
    }

}