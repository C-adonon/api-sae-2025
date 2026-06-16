package future.SAE.application.services;

import future.SAE.application.exception.IdentifiantsInvalidesException;
import future.SAE.application.interfaces.IAuthentificationService;
import future.SAE.application.interfaces.ISecurityProvider;
import future.SAE.domain.model.Utilisateur;
import future.SAE.domain.repository.IUtilisateurRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService implements IAuthentificationService {

    private final IUtilisateurRepository utilisateurRepository;
    private final ISecurityProvider securityProvider;

    public AuthentificationService(IUtilisateurRepository utilisateurRepository, ISecurityProvider securityProvider) {
        this.utilisateurRepository = utilisateurRepository;
        this.securityProvider = securityProvider;
    }

    @Override
    public Utilisateur authentifier(String identifiant, String motDePasseClair) {

        Utilisateur utilisateur = utilisateurRepository.trouverParIdentifiant(identifiant)
                .orElseThrow(IdentifiantsInvalidesException::new);

        boolean motDePasseValide = securityProvider.verifier(motDePasseClair, utilisateur.getMotDePasse());

        if (!motDePasseValide) {
            throw new IdentifiantsInvalidesException();
        }


        return utilisateur;
    }
}