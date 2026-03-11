import future.SAE.domain.valueObject.SuiviCours;
import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Eleve;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestSuiviCours {

    @Test
    @DisplayName("Le constructeur (avec Eleve) doit initialiser correctement les attribus")
    void testConstructeurAvecEleve(){

        Eleve eleve = new Eleve();

        SuiviCours suivi = new SuiviCours(eleve);

        assertEquals(eleve, suivi.getEleve(), "L'élève doit être correctement assigné");
        assertNull(suivi.getDateDernierAcces(), "La date de dernier accès doit être null");
        assertNull(suivi.getCours());


    }
}
