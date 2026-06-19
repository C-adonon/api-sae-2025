package future.SAE.application.services;

import future.SAE.application.exception.UtilisateurIntrouvableException;
import future.SAE.domain.interfaces.IUtilisateurRepository;
import future.SAE.domain.model.Eleve;
import future.SAE.domain.model.Professeur;
import future.SAE.domain.model.Utilisateur;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UtilisateurServiceTest {

    @Mock
    private IUtilisateurRepository utilisateurRepositoryMock;

    @InjectMocks
    private UtilisateurService utilisateurService;

    @Test
    @DisplayName("Doit consulter le profil d'un Professeur avec succès")
    void consulterProfil_Professeur_Succes() {
        UUID idProf = UUID.randomUUID();
        Professeur profMock = new Professeur("Doe", "John", "PROF-001", "john@ecole.fr", "hash");

        when(utilisateurRepositoryMock.trouverParId(idProf)).thenReturn(Optional.of(profMock));

        Utilisateur resultat = utilisateurService.consulterProfil(idProf);

        assertNotNull(resultat);
        assertEquals("Doe", resultat.getNom());
        assertTrue(resultat instanceof Professeur, "Le résultat doit être une instance de Professeur");
        verify(utilisateurRepositoryMock, times(1)).trouverParId(idProf);
    }

    @Test
    @DisplayName("Doit consulter le profil d'un Élève avec succès")
    void consulterProfil_Eleve_Succes() {
        UUID idEleve = UUID.randomUUID();
        Eleve eleveMock = new Eleve("Smith", "Alice", "ELEVE-001", "alice@ecole.fr", "hash");

        when(utilisateurRepositoryMock.trouverParId(idEleve)).thenReturn(Optional.of(eleveMock));

        Utilisateur resultat = utilisateurService.consulterProfil(idEleve);

        assertNotNull(resultat);
        assertEquals("Alice", resultat.getPrenom());
        assertTrue(resultat instanceof Eleve, "Le résultat doit être une instance d'Eleve");
    }

    @Test
    @DisplayName("Doit lever une exception si l'utilisateur est introuvable")
    void consulterProfil_Introuvable() {
        UUID idInconnu = UUID.randomUUID();
        when(utilisateurRepositoryMock.trouverParId(idInconnu)).thenReturn(Optional.empty());

        UtilisateurIntrouvableException exception = assertThrows(UtilisateurIntrouvableException.class, () ->
                utilisateurService.consulterProfil(idInconnu)
        );

        assertTrue(exception.getMessage().contains("introuvable"));
    }

    @Test
    @DisplayName("Doit modifier le nom et prénom d'un utilisateur existant")
    void modifierProfil_Succes() {
        UUID idUser = UUID.randomUUID();
        Eleve eleveExistant = new Eleve("AncienNom", "AncienPrenom", "ELEVE-002", "test@ecole.fr", "hash");

        when(utilisateurRepositoryMock.trouverParId(idUser)).thenReturn(Optional.of(eleveExistant));
        when(utilisateurRepositoryMock.sauvegarder(any(Utilisateur.class))).thenAnswer(i -> i.getArgument(0));

        Utilisateur resultat = utilisateurService.modifierProfil(idUser, "NouveauNom", "NouveauPrenom");

        assertNotNull(resultat);
        assertEquals("NouveauNom", resultat.getNom());
        assertEquals("NouveauPrenom", resultat.getPrenom());
        assertEquals("test@ecole.fr", resultat.getEmail()); // L'email ne doit pas avoir changé

        verify(utilisateurRepositoryMock, times(1)).sauvegarder(eleveExistant);
    }
}