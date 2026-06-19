package future.SAE.application.services;

import future.SAE.application.exception.EmailDejaUtiliseException;
import future.SAE.application.exception.IdentifiantDejaUtiliseException;
import future.SAE.application.exception.IdentifiantsInvalidesException;
import future.SAE.application.interfaces.IInscriptionService;
import future.SAE.application.interfaces.ISecurityProvider;
import future.SAE.domain.model.Eleve;
import future.SAE.domain.model.Professeur;
import future.SAE.domain.model.Utilisateur;
import future.SAE.domain.interfaces.IUtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

        if (utilisateurRepository.trouverParIdentifiant(identifiant).isPresent()) {
            throw new IdentifiantDejaUtiliseException(identifiant);
        }

        if (utilisateurRepository.trouverParEmail(email).isPresent()) {
            throw new EmailDejaUtiliseException(email);
        }

        String motDePasseHache = securityProvider.hacher(motDePasseClair);

        Professeur nouveauProfesseur = new Professeur(nom, prenom, identifiant, email, motDePasseHache);

        return utilisateurRepository.sauvegarder(nouveauProfesseur);
    }

    @Override
    public Eleve inscrireEleve(String identifiant, String nom, String prenom, String email, String motDePasse) {


        if (utilisateurRepository.trouverParIdentifiant(identifiant).isPresent()) {
            throw new IdentifiantDejaUtiliseException("Cet identifiant est déjà utilisé.");
        }
        if (utilisateurRepository.trouverParEmail(email).isPresent()) {
            throw new IdentifiantDejaUtiliseException("Cet email est déjà utilisé.");
        }

        String motDePasseHache = securityProvider.hacher(motDePasse);


        Eleve nouvelEleve = new Eleve(nom, prenom, identifiant, email, motDePasseHache);

        return (Eleve) utilisateurRepository.sauvegarder(nouvelEleve);
    }

}