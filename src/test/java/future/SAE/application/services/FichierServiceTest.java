package future.SAE.application.services;

import future.SAE.application.exception.FichierIntrouvableException;
import future.SAE.domain.interfaces.IFichierRepository;
import future.SAE.domain.model.Fichier;
import future.SAE.domain.model.Section;
import future.SAE.domain.valueObject.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Active Mockito pour simuler les dépendances
public class FichierServiceTest {

    @Mock
    private IFichierRepository fichierRepository;

    @InjectMocks
    private FichierService fichierService;

    private Fichier fichierTest;
    private Section sectionTest;

    @BeforeEach
    void setUp() {
        sectionTest = new Section(1, "Chapitre 1 : Les bases");
        fichierTest = new Fichier("Cours Java", "Introduction", "/files/java.pdf", sectionTest, Type.PDF);
        fichierTest.setId(100L); // On simule un ID généré par la BDD
    }

    @Test
    @DisplayName("Doit créer et sauvegarder un fichier correctement")
    void creerFichier_Succes() {
        // Arrange (Préparation)
        when(fichierRepository.sauvegarder(any(Fichier.class))).thenReturn(fichierTest);

        // Act (Action)
        Fichier resultat = fichierService.creerFichier("Cours Java", "Introduction", "/files/java.pdf", sectionTest, Type.PDF);

        // Assert (Vérification)
        assertNotNull(resultat);
        assertEquals("Cours Java", resultat.getTitre());
        assertEquals(Type.PDF, resultat.getType());
        verify(fichierRepository, times(1)).sauvegarder(any(Fichier.class));
    }

    @Test
    @DisplayName("Doit modifier un fichier existant")
    void modifierFichier_Succes() {
        when(fichierRepository.trouverParId(100L)).thenReturn(Optional.of(fichierTest));
        when(fichierRepository.sauvegarder(any(Fichier.class))).thenReturn(fichierTest);

        Fichier resultat = fichierService.modifierFichier(100L, "Nouveau Titre", "Nouvelle Description");

        assertEquals("Nouveau Titre", resultat.getTitre());
        assertEquals("Nouvelle Description", resultat.getDescription());
        verify(fichierRepository, times(1)).sauvegarder(fichierTest);
    }

    @Test
    @DisplayName("Doit lever une exception si on modifie un fichier inexistant")
    void modifierFichier_Introuvable() {
        when(fichierRepository.trouverParId(999L)).thenReturn(Optional.empty());

        assertThrows(FichierIntrouvableException.class, () ->
                fichierService.modifierFichier(999L, "Titre", "Desc")
        );
        verify(fichierRepository, never()).sauvegarder(any());
    }

    @Test
    @DisplayName("Doit supprimer un fichier existant")
    void supprimerFichier_Succes() {
        when(fichierRepository.trouverParId(100L)).thenReturn(Optional.of(fichierTest));

        fichierService.supprimerFichier(100L);

        verify(fichierRepository, times(1)).supprimer(fichierTest);
    }

    @Test
    @DisplayName("Doit récupérer la liste de tous les fichiers")
    void listerFichier_Succes() {
        when(fichierRepository.trouverTous()).thenReturn(List.of(fichierTest));

        List<Fichier> resultats = fichierService.listerFichier();

        assertEquals(1, resultats.size());
        assertEquals("Cours Java", resultats.get(0).getTitre());
    }
}