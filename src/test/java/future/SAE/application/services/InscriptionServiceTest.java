package future.SAE.application.services;

import future.SAE.application.exception.EmailDejaUtiliseException;
import future.SAE.application.exception.IdentifiantDejaUtiliseException;
import future.SAE.application.interfaces.ISecurityProvider;
import future.SAE.domain.model.Professeur;
import future.SAE.domain.model.Utilisateur;
import future.SAE.domain.interfaces.IUtilisateurRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InscriptionServiceTest {

    @Mock
    private IUtilisateurRepository utilisateurRepositoryMock;

    @Mock
    private ISecurityProvider securityProviderMock;

    @InjectMocks
    private InscriptionService inscriptionService;

    @Test
    @DisplayName("Doit inscrire un professeur avec succès")
    void inscrireProfesseur_Succes() {

        String identifiant = "PROF-001";
        String email = "prof@ecole.fr";

        when(utilisateurRepositoryMock.trouverParIdentifiant(identifiant)).thenReturn(Optional.empty());
        when(utilisateurRepositoryMock.trouverParEmail(email)).thenReturn(Optional.empty());
        when(securityProviderMock.hacher("motDePasseSuperSecret")).thenReturn("HASH_12345");


        when(utilisateurRepositoryMock.sauvegarder(any(Professeur.class))).thenAnswer(i -> i.getArgument(0));


        Utilisateur resultat = inscriptionService.inscrireProfesseur(
                identifiant, "Doe", "John", email, "motDePasseSuperSecret"
        );


        assertNotNull(resultat);
        assertEquals(identifiant, resultat.getIdentifiant());
        assertEquals("Doe", resultat.getNom());
        assertEquals("John", resultat.getPrenom());
        assertEquals(email, resultat.getEmail());
        assertEquals("HASH_12345", resultat.getMotDePasse());

        verify(utilisateurRepositoryMock, times(1)).sauvegarder(any(Professeur.class));
    }

    @Test
    @DisplayName("Doit lever une exception si l'identifiant existe déjà")
    void inscrireProfesseur_IdentifiantExistant() {

        String identifiant = "PROF-001";
        when(utilisateurRepositoryMock.trouverParIdentifiant(identifiant))
                .thenReturn(Optional.of(mock(Professeur.class)));


        IdentifiantDejaUtiliseException exception = assertThrows(IdentifiantDejaUtiliseException.class, () ->
                inscriptionService.inscrireProfesseur(identifiant, "Doe", "John", "prof@ecole.fr", "mdp123")
        );

        // vérifier que le message contient bien l'identifiant
        assertTrue(exception.getMessage().contains("PROF-001"));
        verify(utilisateurRepositoryMock, never()).sauvegarder(any());
    }

    @Test
    @DisplayName("Doit lever une exception si l'email existe déjà")
    void inscrireProfesseur_EmailExistant() {

        String email = "prof@ecole.fr";
        when(utilisateurRepositoryMock.trouverParIdentifiant("PROF-002")).thenReturn(Optional.empty());
        when(utilisateurRepositoryMock.trouverParEmail(email))
                .thenReturn(Optional.of(mock(Professeur.class)));


        EmailDejaUtiliseException exception = assertThrows(EmailDejaUtiliseException.class, () ->
                inscriptionService.inscrireProfesseur("PROF-002", "Doe", "John", email, "mdp123")
        );

        assertTrue(exception.getMessage().contains("prof@ecole.fr"));
        verify(utilisateurRepositoryMock, never()).sauvegarder(any());
    }
}