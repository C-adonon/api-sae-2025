import future.SAE.domain.model.Formation;
import future.SAE.domain.model.Competence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;


public class TestCompetence {

    @Test
    @DisplayName("Test du constructeur (avec description) du model \"Competence.java\"")
    void testConstructeurAvecDescription(){

        Formation formation = new Formation();
        int numero = 1;
        String libelle = "Développement d'application";
        String description = "API + client responsive";

        Competence competence = new Competence(numero,libelle,formation,description);

        assertAll("Vérification des attributs de la compétence",
                () -> assertEquals(numero, competence.getNumero()),
                () -> assertEquals(libelle, competence.getLibelle()),
                () -> assertEquals(description, competence.getDescription()),
                () -> assertEquals(formation, competence.getFormation())
        );
    }

    @Test
    @DisplayName("Test du constructeur sans description du model \"Competence.java\"")
    void testConstructeurSansDescription(){
        Formation formation = new Formation();

        Competence competence = new Competence(5,"Optimisation",formation);

        assertNull(competence.getDescription(), "La description devrait être null");

    }

    @Test
    @DisplayName("Test de la méthode toString() du model \"Competence.java\"")
    void testToString(){
        Competence competence = new Competence(3, "Administration de systèmes", null, "Conteneurisation");

        String attendu = "Compétence C3\nLibellé : Administration de systèmes\nDescription : Conteneurisation\n";
    }


}
