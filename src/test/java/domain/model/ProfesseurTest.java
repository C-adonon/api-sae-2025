package domain.model;

import future.SAE.domain.exception.CoursDejaAssigneException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Formation;
import future.SAE.domain.model.Professeur;
import future.SAE.domain.valueObject.Semestre;

import static org.junit.jupiter.api.Assertions.*;


public class ProfesseurTest
{

    String nom,prenom,email,mdp;
    String identifiant;
    Professeur p;

    @BeforeEach
    public void initProfesseur()
    {
        nom = "Edoh-Dagnon";
        prenom = "Clarence";
        identifiant = "clarence.edoh-dagnon" ;
        email = "clarence@example.com";
        mdp = "motdepasse";
        p = new Professeur(nom, prenom, identifiant, email, mdp);
    }

    @Test
    public void testAttributs(){
        assertEquals(p.getNom(),nom);
        assertEquals(p.getPrenom(), prenom);
        assertEquals(p.getEmail(), email);
        assertEquals(p.getMotDePasse(), mdp);
        assertEquals(p.getIdentifiant(), identifiant);
    }

    @Test
    public void testDevenirResponsableFormation() {

        Formation formation = new Formation(1, "BUT Informatique", null, Semestre.S1);

        p.nommerResponsableDe(formation);

        assertNotNull(p.getFormationSupervisee());
        assertEquals("BUT Informatique", p.getFormationSupervisee().getNom());
        // la formation doit aussi savoir que 'p' est son responsable
        assertEquals(p, formation.getResponsable());
    }
    @Test
    public void testAjouter_SupprimerCours(){
        Formation f1 = new Formation(1, "BUT Informatique", p, Semestre.S1);
        // Vérifier que la liste est vide au départ
        assertEquals(0, p.getCoursDispenses().size());
        
        // Ajouter un cours
        Cours cours1 = new Cours("Algorithmique", p, f1);
        p.ajouterCoursDispense(cours1);
        
        // Vérifier que le cours a été ajouté
        assertEquals(1, p.getCoursDispenses().size());
        assertEquals(cours1, p.getCoursDispenses().get(0));
        
        // Ajouter un second cours
        Cours cours2 = new Cours("Programmation", p, f1);
        p.ajouterCoursDispense(cours2);
        assertEquals(2, p.getCoursDispenses().size());
        
        // Tester l'ajout d'un cours null (ne doit pas être ajouté)
        p.ajouterCoursDispense(null);
        assertEquals(2, p.getCoursDispenses().size());

        Professeur autreProf = new Professeur("Dupont", "Jean", "dupont.jean", "jean@example.com", "pass");
        Cours coursDunAutre = new Cours("Base de données", autreProf, f1);

        CoursDejaAssigneException exception = assertThrows(
                CoursDejaAssigneException.class,
                () -> p.ajouterCoursDispense(coursDunAutre)
        );
        assertEquals("Impossible d'ajouter le cours '" + coursDunAutre.getNom() + "' : il est déjà dispensé par un autre professeur.", exception.getMessage());
    }

    @Test
    public void testModifierMotDePasse() {
        // Act
        boolean modifie = p.modifierMdp("motdepasse", "NouveauSuperMdp456");

        // Assert
        assertTrue(modifie);
        assertEquals("NouveauSuperMdp456", p.getMotDePasse());
    }

    @Test
    public void testModifierMotDePasse_AncienIncorrect() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> p.modifierMdp("mauvaisAncienMdp", "NouveauSuperMdp456")
        );
        assertEquals(mdp, p.getMotDePasse());
    }

    @Test
    public void testSupprimerCoursDispense_Success() {
        Formation f1 = new Formation(1, "BUT Informatique", p, Semestre.S1);
        Cours cours1 = new Cours("Algorithmique", p, f1);


        p.ajouterCoursDispense(cours1);
        assertEquals(1, p.getCoursDispenses().size());


        p.supprimerCoursDispense(cours1);


        assertEquals(0, p.getCoursDispenses().size());
        assertNull(cours1.getProfesseur());
    }

    @Test
    public void testSupprimerCoursDispense_Null() {
        Formation f1 = new Formation(1, "BUT Informatique", p, Semestre.S1);
        Cours cours1 = new Cours("Algorithmique", p, f1);
        p.ajouterCoursDispense(cours1);


        p.supprimerCoursDispense(null);


        assertEquals(1, p.getCoursDispenses().size());
    }

    @Test
    public void testSupprimerCoursDispense_MauvaisProfesseur() {
        Formation f1 = new Formation(1, "BUT Informatique", p, Semestre.S1);

        Professeur autreProf = new Professeur("Dupont", "Jean", "dupont.jean", "jean@example.com", "pass");
        Cours coursDunAutre = new Cours("Base de données", autreProf, f1);

        CoursDejaAssigneException exception = assertThrows(
                CoursDejaAssigneException.class,
                () -> p.supprimerCoursDispense(coursDunAutre)
        );
        assertEquals("Impossible d'ajouter le cours '" + coursDunAutre.getNom() + "' : il est déjà dispensé par un autre professeur.", exception.getMessage());
    }


}
