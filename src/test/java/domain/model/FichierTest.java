package domain.model;

import future.SAE.domain.model.Fichier;
import future.SAE.domain.model.Section;
import future.SAE.domain.valueObject.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FichierTest {

    private String titre;
    private String description;
    private String chemin;
    private Type type;
    private Section section;
    private Fichier fichier;

    @BeforeEach
    public void init() {
        titre = "Support de cours";
        description = "Introduction à Java";
        chemin = "/files/java_intro.pdf";
        type = Type.PDF;
        section = new Section(1, "Chapitre 1");
        fichier = new Fichier(titre, description, chemin, section, type);
    }

    @Test
    public void testConstructeurComplet() {
        assertEquals(titre, fichier.getTitre());
        assertEquals(description, fichier.getDescription());
        assertEquals(chemin, fichier.getCheminFichier());
        assertEquals(section, fichier.getSection());
        assertEquals(type, fichier.getType());
        assertNotNull(fichier.getDatePublication());
    }

    @Test
    public void testConstructeurSansDescription() {
        Fichier f = new Fichier("Video", "video.mp4", section, Type.VIDEO);
        assertEquals("Video", f.getTitre());
        assertNull(f.getDescription());
        assertEquals("video.mp4", f.getCheminFichier());
        assertEquals(Type.VIDEO, f.getType());
    }

    @Test
    public void testSetters() {
        fichier.setTitre("Nouveau Titre");
        fichier.setDescription("Nouvelle Description");
        fichier.setCheminFichier("new_path.pdf");
        fichier.setType(Type.LIEN);
        
        assertEquals("Nouveau Titre", fichier.getTitre());
        assertEquals("Nouvelle Description", fichier.getDescription());
        assertEquals("new_path.pdf", fichier.getCheminFichier());
        assertEquals(Type.LIEN, fichier.getType());
    }

    @Test
    public void testToString() {
        String expected = "Fichier: " + titre + "." + type;
        assertEquals(expected, fichier.toString());
    }
}
