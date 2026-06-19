package future.SAE.domain.model;

import future.SAE.domain.exception.FormationInvalideException;
import future.SAE.domain.exception.InscriptionInvalideException;
import future.SAE.domain.valueObject.Semestre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EleveTest {

    private Eleve eleve;
    private Formation formation;

    @BeforeEach
    public void setUp() {
        formation = new Formation(1, "Informatique", null, Semestre.S1);
        eleve = new Eleve("BENDJEBBOUR", "Yasmine", "12301458", "yasmine.bendjebbour@edu.univ.fr", "Y@smine2005!");
        if (eleve.getInscriptions() == null) {
            eleve.setInscriptions(new ArrayList<>());
        }
    }

    @Test
    @DisplayName("Doit instancier un élève avec les bonnes informations de base")
    public void creerEleve_Succes() {
        assertEquals("BENDJEBBOUR", eleve.getNom(), "Le nom doit être BENDJEBBOUR");
        assertEquals("Yasmine", eleve.getPrenom(), "Le prénom doit être Yasmine");
        assertEquals("12301458", eleve.getIdentifiant(), "L'identifiant doit être 12301458");
        assertEquals("yasmine.bendjebbour@edu.univ.fr", eleve.getEmail(), "L'adresse mail doit correspondre");
    }

    @Test
    @DisplayName("Doit pouvoir assigner une formation à un élève")
    public void assignerFormation_Succes() {
        Eleve nouvelEleve = new Eleve("ADONON", "Chloe", "12512557", "chloe.adonon@edu.univ.fr", "Chl0e2000!");


        nouvelEleve.inscriptionFormation(formation);

        assertNotNull(nouvelEleve.getFormation(), "La formation ne doit pas être nulle");
        assertEquals(formation, nouvelEleve.getFormation(), "L'élève doit être assigné à la formation Informatique");
        assertEquals("Informatique", nouvelEleve.getFormation().getNom(), "Le nom de la formation doit être Informatique");
    }

    @Test
    @DisplayName("Doit lever une exception si l'élève s'inscrit à sa formation actuelle")
    public void inscriptionFormation_DejaInscrit_Exception() {
        eleve.inscriptionFormation(formation);


        FormationInvalideException exception = assertThrows(FormationInvalideException.class, () ->
                eleve.inscriptionFormation(formation)
        );

        assertTrue(exception.getMessage().contains("déjà inscrit"));
    }

    @Test
    @DisplayName("Doit pouvoir ajouter une inscription via la méthode métier de l'élève")
    public void ajouterInscription_Succes() {
        Cours cours = new Cours();
        cours.setNom("Développement Web");

        InscriptionCours inscription = new InscriptionCours();
        inscription.setCours(cours);
        inscription.setEleve(eleve);


        eleve.inscriptionCours(inscription);

        assertFalse(eleve.getInscriptions().isEmpty(), "La liste d'inscriptions ne doit plus être vide");
        assertEquals(1, eleve.getInscriptions().size(), "L'élève doit avoir exactement 1 inscription");
        assertEquals("Développement Web", eleve.getInscriptions().get(0).getCours().getNom(), "L'inscription doit correspondre au bon cours");
    }

    @Test
    @DisplayName("Doit lever une exception si l'élève s'inscrit deux fois au même cours")
    public void ajouterInscription_DejaInscrit_Exception() {
        Cours cours = new Cours();
        cours.setNom("Développement Web");

        InscriptionCours inscription1 = new InscriptionCours();
        inscription1.setCours(cours);
        inscription1.setEleve(eleve);

        InscriptionCours inscription2 = new InscriptionCours();
        inscription2.setCours(cours);
        inscription2.setEleve(eleve);

        // premiere insciption
        eleve.inscriptionCours(inscription1);

        // deuxieme inscription (doit lever une exception)
        InscriptionInvalideException exception = assertThrows(InscriptionInvalideException.class, () ->
                eleve.inscriptionCours(inscription2)
        );

        assertTrue(exception.getMessage().contains("déjà inscrit"));
        assertEquals(1, eleve.getInscriptions().size());
    }

    @Test
    @DisplayName("Doit pouvoir supprimer une inscription existante")
    public void supprimerInscription_Succes() {
        Cours cours = new Cours();
        cours.setNom("Développement Web");
        InscriptionCours inscription = new InscriptionCours();
        inscription.setCours(cours);
        inscription.setEleve(eleve);

        eleve.inscriptionCours(inscription);

        // Act
        eleve.supprimerInscription(inscription);


        assertTrue(eleve.getInscriptions().isEmpty(), "La liste d'inscriptions doit être vide après suppression");
    }

    @Test
    @DisplayName("Doit lever une exception si on tente de supprimer une inscription inexistante")
    public void supprimerInscription_Inexistante_Exception() {
        InscriptionCours inscriptionFantome = new InscriptionCours();
        Cours cours = new Cours();
        cours.setNom("Cours Inconnu");
        inscriptionFantome.setCours(cours);


        InscriptionInvalideException exception = assertThrows(InscriptionInvalideException.class, () ->
                eleve.supprimerInscription(inscriptionFantome)
        );

        assertTrue(exception.getMessage().contains("pas inscrit à ce cours"));
    }
}