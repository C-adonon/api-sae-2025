package future.SAE.application.services;

import future.SAE.application.exception.AccesRefuseException;
import future.SAE.application.exception.CoursIntrouvableException;
import future.SAE.application.interfaces.ICoursService;
import future.SAE.application.interfaces.IUtilisateurCourantProvider;
import future.SAE.domain.interfaces.ICoursRepository;
import future.SAE.domain.interfaces.IInscriptionCoursRepository;
import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Eleve;
import future.SAE.domain.model.Professeur;
import future.SAE.domain.model.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursService implements ICoursService {

    private final ICoursRepository coursRepository;
    private final IInscriptionCoursRepository inscriptionRepository;
    private final IUtilisateurCourantProvider utilisateurCourant;

    public CoursService(ICoursRepository coursRepository,
                        IInscriptionCoursRepository inscriptionRepository,
                        IUtilisateurCourantProvider utilisateurCourant) {
        this.coursRepository = coursRepository;
        this.inscriptionRepository = inscriptionRepository;
        this.utilisateurCourant = utilisateurCourant;
    }

    @Override
    public Cours creerCours(String nom) {
        Cours c = new Cours(nom, null, null);
        return coursRepository.sauvegarder(c);
    }

    @Override
    public Cours modifierCours(Long id, String nouveauNom) {

        Cours c = coursRepository.trouverParId(id).orElseThrow(CoursIntrouvableException::new);
        c.setNom(nouveauNom);
        return coursRepository.sauvegarder(c);
    }

    @Override
    public void supprimerCours(Long id) {
        Cours c = coursRepository.trouverParId(id).orElseThrow(CoursIntrouvableException::new);
        coursRepository.supprimer(c);
    }

    @Override
    public Cours accederCours(Long id) {
        Cours cours = coursRepository.trouverParId(id).orElseThrow(CoursIntrouvableException::new);
        verifierAccesAuCours(cours);
        return cours;
    }

    @Override
    public List<Cours> listerCours() {
        return coursRepository.trouverTous();
    }

    /**
     * Règle d'accès : un professeur peut toujours consulter un cours. Un élève ne peut
     * consulter un cours non public que s'il y est inscrit. Un cours public est ouvert
     * à tout utilisateur authentifié.
     */
    private void verifierAccesAuCours(Cours cours) {
        Utilisateur courant = utilisateurCourant.utilisateurCourant();

        if (courant instanceof Professeur) {
            return;
        }

        if (cours.isPublique()) {
            return;
        }

        if (courant instanceof Eleve
                && inscriptionRepository.estInscrit(courant.getId(), cours.getIdCours())) {
            return;
        }

        throw new AccesRefuseException("Vous devez être inscrit à ce cours pour y accéder.");
    }
}
