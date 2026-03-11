import future.SAE.domain.model.Eleve;
import future.SAE.domain.model.Section;
import future.SAE.domain.valueObject.EtatSection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class TestEtatSection {

    @Test
    @DisplayName("Un nouvel objet EtatSection doit etre marqué comme non terminé")
    void testInitialisation(){

        Eleve eleve = new Eleve();
        Section section = new Section();

        EtatSection etat = new EtatSection(eleve,section);

        assertFalse(etat.isEstTerminee(), "La section doit pas être terminée par défaut");
        assertNull(etat.getDateCompletion(), "Par défaut, la date de complétion doit être nulle");
        assertEquals(eleve, etat.getEleve());
        assertEquals(section, etat.getSection());
    }

    @Test
    @DisplayName("marquerCommeTerminee() doit changer la valeur de estTerminee et assigner la date de complétion")
    void testMarquerCommeTerminee(){

        EtatSection etat = new EtatSection(new Eleve(), new Section());

        etat.marquerCommeTerminee();

        assertTrue(etat.isEstTerminee(), "Le statut doit passer a True");
        assertNotNull(etat.getDateCompletion(), "La date de complétion ne doit plus être null");
    }

    @Test
    @DisplayName("Rappeler marquerCommeTerminee() ne doit pas écraser la date initiale")
    void testDateCompletionPasEcrasee(){

        EtatSection etat = new EtatSection(new Eleve(), new Section());
        LocalDateTime dateHier = LocalDateTime.now().minusDays(1);
        etat.setEstTerminee(true);
        etat.setDateCompletion(dateHier);

        etat.marquerCommeTerminee();

        assertTrue(etat.isEstTerminee());
        assertEquals(dateHier, etat.getDateCompletion(), "La date de complétion ne doit pas avoir changée si estTerminee etait déja true");
    }
}
