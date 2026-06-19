package future.SAE.application.interfaces;

import java.util.Optional;

import future.SAE.domain.model.Utilisateur;

/**
 * Port donnant accès à l'utilisateur actuellement authentifié.
 * L'implémentation s'appuie sur le contexte de sécurité (JWT) de l'infrastructure.
 */
public interface IUtilisateurCourantProvider {

    /** Retourne l'utilisateur authentifié ou lève une exception si personne n'est connecté. */
    Utilisateur utilisateurCourant();

    /** Retourne l'utilisateur authentifié s'il existe. */
    Optional<Utilisateur> trouverUtilisateurCourant();
}
