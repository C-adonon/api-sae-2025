package future.SAE.application.services;

import future.SAE.application.exception.IdentifiantsInvalidesException;
import future.SAE.application.interfaces.ISecurityProvider;
import future.SAE.domain.model.Utilisateur;
import future.SAE.domain.repository.IUtilisateurRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthentificationServiceTest {

    @Mock
    private IUtilisateurRepository utilisateurRepositoryMock;

    @Mock
    private ISecurityProvider securityProviderMock;

    @InjectMocks
    private AuthentificationService authentificationService;

    @Test
    @DisplayName("Doit réussir l'authentification avec les bons identifiants")
    void authentifier_Succes() {
        String identifiant = "PROF-123";
        String motDePasseClair = "secret123";
        String motDePasseHache = "HASH_SECRET";

        Utilisateur utilisateurMock = new Utilisateur("Doe", "John", identifiant, "john@ecole.fr", motDePasseHache);

        when(utilisateurRepositoryMock.trouverParIdentifiant(identifiant)).thenReturn(Optional.of(utilisateurMock));
        when(securityProviderMock.verifier(motDePasseClair, motDePasseHache)).thenReturn(true);


        Utilisateur resultat = authentificationService.authentifier(identifiant, motDePasseClair);

        assertNotNull(resultat);
        assertEquals(identifiant, resultat.getIdentifiant());
    }

    @Test
    @DisplayName("Doit échouer si l'identifiant n'existe pas")
    void authentifier_IdentifiantInconnu() {
        when(utilisateurRepositoryMock.trouverParIdentifiant("INCONNU")).thenReturn(Optional.empty());

        assertThrows(IdentifiantsInvalidesException.class, () ->
                authentificationService.authentifier("INCONNU", "mdp123")
        );

        // On vérifie que la sécurité n'est même pas appelée si l'utilisateur n'existe pas
        verify(securityProviderMock, never()).verifier(anyString(), anyString());
    }

    @Test
    @DisplayName("Doit échouer si le mot de passe est incorrect")
    void authentifier_MotDePasseIncorrect() {

        String identifiant = "PROF-123";
        Utilisateur utilisateurMock = new Utilisateur("Doe", "John", identifiant, "john@ecole.fr", "VRAI_HASH");

        when(utilisateurRepositoryMock.trouverParIdentifiant(identifiant)).thenReturn(Optional.of(utilisateurMock));
        when(securityProviderMock.verifier("MAUVAIS_MDP", "VRAI_HASH")).thenReturn(false);

        assertThrows(IdentifiantsInvalidesException.class, () ->
                authentificationService.authentifier(identifiant, "MAUVAIS_MDP")
        );
    }
}