import future.SAE.domain.valueObject.SuiviCours;
import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Eleve;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestSuiviCours {

    @Test
    @DisplayName("Le constructeur doit initialiser correctement les attribus")
    void testConstructeur(){

        Eleve eleve = new Eleve();
        Cours cours = new Cours();

        SuiviCours suivi = new SuiviCours(eleve, cours);

        assertEquals(eleve, suivi.getEleve(), "L'élève doit être correctement assigné");
        assertNotNull(suivi.getDateDernierAcces(), "La date de dernier accès ne doit pas être null");
        assertEquals(cours, suivi.getCours(), "Le cours doit avoir été assigné correctement");
    }
}
