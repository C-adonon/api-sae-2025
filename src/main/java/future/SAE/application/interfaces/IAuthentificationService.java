package future.SAE.application.interfaces;

import future.SAE.domain.model.Utilisateur;

public interface IAuthentificationService {
    public Utilisateur authentifier(String identifiant, String motDePasseClair);
}