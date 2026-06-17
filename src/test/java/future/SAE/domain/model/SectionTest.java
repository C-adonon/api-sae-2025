package future.SAE.domain.model;

import future.SAE.domain.valueObject.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

public class SectionTest {
    private Section section;

    @BeforeEach
    void init() {
        section = new Section(1, "Introduction au DDD", "Ce chapitre présente les bases.");
    }

    @Test
    void testInitialisationParDefaut() {
        assertEquals(1, section.getOrdre());
        assertEquals("Introduction au DDD", section.getTitre());
        // une nouvelle section n'est pas visible par les élèves
        assertFalse(section.isOuverte());
        // La liste des fichiers doit être vide, mais pas null
        assertNotNull(section.getFichiers());
        assertTrue(section.getFichiers().isEmpty());
    }

    @Test
    @DisplayName("Doit permettre d'ouvrir et de fermer une section")
    void testChangementEtatOuverture() {

        section.ouvrirSection();
        assertTrue(section.isOuverte());

        section.fermerSection();
        assertFalse(section.isOuverte());
    }

    @Test
    void testAjouterFichier_Succes() {
        Fichier fichier = new Fichier("Support PDF", "Chemin/vers/le/pdf", null,Type.PDF);

        section.ajouterFichier(fichier);


        assertEquals(1, section.getFichiers().size());
        assertTrue(section.getFichiers().contains(fichier));
    }

    @Test
    @DisplayName("Doit ignorer l'ajout si le fichier est null")
    void testAjouterFichier_Null() {
        section.ajouterFichier(null);

        assertEquals(0, section.getFichiers().size());
    }
}
