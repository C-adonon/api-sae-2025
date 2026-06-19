package future.SAE.infrastructure.config;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import future.SAE.application.exception.AccesRefuseException;
import future.SAE.application.interfaces.IUtilisateurCourantProvider;
import future.SAE.domain.interfaces.IUtilisateurRepository;
import future.SAE.domain.model.Utilisateur;

/**
 * Implémentation du port {@link IUtilisateurCourantProvider} : récupère l'identifiant
 * placé dans le contexte de sécurité par le {@link JwtAuthenticationFilter} puis
 * recharge l'utilisateur du domaine via son dépôt.
 */
@Component
public class UtilisateurCourantProvider implements IUtilisateurCourantProvider {

    private final IUtilisateurRepository utilisateurRepository;

    public UtilisateurCourantProvider(IUtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur utilisateurCourant() {
        return trouverUtilisateurCourant()
                .orElseThrow(() -> new AccesRefuseException("Aucun utilisateur authentifié."));
    }

    @Override
    public Optional<Utilisateur> trouverUtilisateurCourant() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof String identifiant) || identifiant.isBlank()) {
            return Optional.empty();
        }
        return utilisateurRepository.trouverParIdentifiant(identifiant);
    }
}
