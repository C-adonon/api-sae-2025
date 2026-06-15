package future.SAE.application.interfaces;

import future.SAE.domain.model.Utilisateur;

public interface IInscriptionService {
    Utilisateur inscrireProfesseur(String matricule, String nom, String prenom, String email, String motDePasseClair);

}
