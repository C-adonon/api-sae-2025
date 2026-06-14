package domain.model;

import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Formation;
import future.SAE.domain.model.Professeur;
import future.SAE.domain.model.Section;
import future.SAE.domain.valueObject.Semestre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoursTest {

    Cours cours;
    Professeur professeur;

    @BeforeEach
    public void init() {
        String nom = "Architecture Logicielle";
        String description = "Cours de conception et d'architecture logicielle";
        Formation f = new Formation(1,"Formation de conception et d'architecture logicielle",professeur, Semestre.S1);
        professeur = new Professeur("Edoh-Dagnon","Clarence","clarence.edoh-dagnon","clarence@example.com","motDePasse");
        cours = new Cours(nom, description, professeur);
    }

    @Test
    @DisplayName("Doit ajouter une section valide au cours")
    void testAjouterSection_Succes() {
        // Arrange
        Section section1 = new Section(1, "Introduction");

        // Act
        cours.ajouterSection(section1);

        // Assert
        assertEquals(1, cours.getSections().size());
        assertTrue(cours.getSections().contains(section1));
        // On vérifie la relation bidirectionnelle !
        assertEquals(cours, section1.getCours());
    }

    @Test
    @DisplayName("Doit refuser d'ajouter une section avec un ordre déjà existant")
    void testAjouterSection_OrdreExistant() {
        // Arrange
        Section section1 = new Section(1, "Introduction");
        Section section2 = new Section(1, "Autre introduction avec le même numéro");

        cours.ajouterSection(section1); // Le premier ajout passe

        // Act & Assert : Le deuxième ajout doit lever une exception
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> cours.ajouterSection(section2)
        );

        assertEquals("Une section avec l'ordre 1 existe déjà dans ce cours.", exception.getMessage());
        // L'état du cours n'a pas été corrompu
        assertEquals(1, cours.getSections().size());
    }

    @Test
    @DisplayName("Doit ignorer silencieusement l'ajout d'une section null")
    void testAjouterSection_Null() {
        // Act
        cours.ajouterSection(null);

        // Assert
        assertEquals(0, cours.getSections().size());
    }
}
}
