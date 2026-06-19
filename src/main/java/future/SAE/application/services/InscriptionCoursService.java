package future.SAE.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import future.SAE.application.exception.AccesRefuseException;
import future.SAE.application.exception.CoursIntrouvableException;
import future.SAE.application.exception.DejaInscritException;
import future.SAE.application.exception.InscriptionCoursIntrouvableException;
import future.SAE.application.interfaces.IInscriptionCoursService;
import future.SAE.application.interfaces.IUtilisateurCourantProvider;
import future.SAE.domain.interfaces.ICoursRepository;
import future.SAE.domain.interfaces.IInscriptionCoursRepository;
import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Eleve;
import future.SAE.domain.model.InscriptionCours;
import future.SAE.domain.model.Professeur;
import future.SAE.domain.model.Utilisateur;

@Service
public class InscriptionCoursService implements IInscriptionCoursService {

    private final IInscriptionCoursRepository inscriptionRepository;
    private final ICoursRepository coursRepository;
    private final IUtilisateurCourantProvider utilisateurCourant;

    public InscriptionCoursService(IInscriptionCoursRepository inscriptionRepository,
                                   ICoursRepository coursRepository,
                                   IUtilisateurCourantProvider utilisateurCourant) {
        this.inscriptionRepository = inscriptionRepository;
        this.coursRepository = coursRepository;
        this.utilisateurCourant = utilisateurCourant;
    }

    @Override
    public InscriptionCours inscrireCoursCourant(Long coursId) {
        Eleve eleve = eleveCourant();

        Cours cours = coursRepository.trouverParId(coursId).orElseThrow(CoursIntrouvableException::new);

        if (inscriptionRepository.estInscrit(eleve.getId(), coursId)) {
            throw new DejaInscritException();
        }

        InscriptionCours inscription = new InscriptionCours(cours, eleve);
        return inscriptionRepository.sauvegarder(inscription);
    }

    @Override
    public void desinscrire(Long inscriptionId) {
        InscriptionCours inscription = inscriptionRepository.trouverParId(inscriptionId)
                .orElseThrow(InscriptionCoursIntrouvableException::new);

        Utilisateur courant = utilisateurCourant.utilisateurCourant();
        boolean estProprietaire = inscription.getEleve() != null
                && inscription.getEleve().getId() != null
                && inscription.getEleve().getId().equals(courant.getId());

        if (!(courant instanceof Professeur) && !estProprietaire) {
            throw new AccesRefuseException("Vous ne pouvez pas supprimer cette inscription.");
        }

        inscriptionRepository.supprimer(inscription);
    }

    @Override
    public List<InscriptionCours> listerMesInscriptions() {
        Eleve eleve = eleveCourant();
        return inscriptionRepository.trouverParEleve(eleve.getId());
    }

    @Override
    public List<InscriptionCours> listerInscriptionsParCours(Long coursId) {
        Utilisateur courant = utilisateurCourant.utilisateurCourant();
        if (!(courant instanceof Professeur)) {
            throw new AccesRefuseException("Seul un professeur peut consulter les inscrits d'un cours.");
        }
        return inscriptionRepository.trouverParCours(coursId);
    }

    private Eleve eleveCourant() {
        Utilisateur courant = utilisateurCourant.utilisateurCourant();
        if (!(courant instanceof Eleve eleve)) {
            throw new AccesRefuseException("Seul un élève peut effectuer cette action.");
        }
        return eleve;
    }
}
