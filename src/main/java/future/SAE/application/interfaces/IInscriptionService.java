package future.SAE.application.interfaces;

import future.SAE.domain.model.Utilisateur;

public interface IInscriptionService {
    public Utilisateur inscrireProfesseur(String identifiant, String nom, String prenom, String email, String motDePasseClair);

}
