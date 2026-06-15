package future.SAE.application.services;

import future.SAE.application.exception.EmailDejaUtiliseException;
import future.SAE.application.exception.IdentifiantDejaUtiliseException;
import future.SAE.application.interfaces.IInscriptionService;
import future.SAE.application.interfaces.ISecurityProvider;
import future.SAE.domain.model.Professeur;
import future.SAE.domain.model.Utilisateur;
import future.SAE.domain.repository.IUtilisateurRepository;
import org.springframework.stereotype.Service;

@Service
public class InscriptionService implements IInscriptionService {

    private final IUtilisateurRepository utilisateurRepository;
    private final ISecurityProvider securityProvider;

    public InscriptionService(IUtilisateurRepository utilisateurRepository, ISecurityProvider securityProvider) {
        this.utilisateurRepository = utilisateurRepository;
        this.securityProvider = securityProvider;
    }

    @Override
    public Utilisateur inscrireProfesseur(String identifiant, String nom, String prenom, String email, String motDePasseClair) {

        // Vérification de l'unicité de l'identifiant
        if (utilisateurRepository.trouverParIdentifiant(identifiant).isPresent()) {
            throw new IdentifiantDejaUtiliseException(identifiant);
        }

        // Vérification de l'unicité de l'email
        if (utilisateurRepository.trouverParEmail(email).isPresent()) {
            throw new EmailDejaUtiliseException(email);
        }

        // Hachage du mot de passe
        String motDePasseHache = securityProvider.hacher(motDePasseClair);

        // Création de l'entité du Domaine (Ordre: nom, prenom, identifiant, email, mdp)
        Professeur nouveauProfesseur = new Professeur(nom, prenom, identifiant, email, motDePasseHache);

        // Sauvegarde via le repository et retour du résultat
        return utilisateurRepository.sauvegarder(nouveauProfesseur);
    }
}