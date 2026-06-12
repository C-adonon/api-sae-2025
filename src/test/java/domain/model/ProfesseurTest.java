package future.SAE.domain.model;

import future.SAE.domain.valueObject.Semestre;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class ProfesseurTest
{

    String nom,prenom,email,mdp;
    int identifiant;
    Professeur p;

    @BeforeEach
    public void initProfesseur()
    {
        nom = "Edoh-Dagnon";
        prenom = "Clarence";
        identifiant = 1 ;
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
    //à revoir
    public void testFormationSupervisee(){
        p.setformationSupervisee(new Formation(1, "BUT Informatique", p, Semestre.S1));
        Formation f1 = p.getFormationSupervisee();
        assertEquals(f1.getNom(), "BUT Informatique");
        assertEquals(f1.getResponsable(), p);
        assertEquals(f1.getSemestre(), Semestre.S1);

    }

    @Test
    public void testAjouterCours(){
        // Vérifier que la liste est vide au départ
        assertEquals(0, p.getCoursDispenses().size());
        
        // Ajouter un cours
        Cours cours1 = new Cours("Algorithmique", p);
        p.ajouterCoursDispense(cours1);
        
        // Vérifier que le cours a été ajouté
        assertEquals(1, p.getCoursDispenses().size());
        assertEquals(cours1, p.getCoursDispenses().get(0));
        
        // Ajouter un second cours
        Cours cours2 = new Cours("Programmation", p);
        p.ajouterCoursDispense(cours2);
        assertEquals(2, p.getCoursDispenses().size());
        
        // Tester l'ajout d'un cours null (ne doit pas être ajouté)
        p.ajouterCoursDispense(null);
        assertEquals(2, p.getCoursDispenses().size());
    }

    
}
