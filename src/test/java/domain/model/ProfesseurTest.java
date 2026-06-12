package future.SAE.domain.model;

import future.SAE.domain.model.Formation;
import future.SAE.domain.model.Professeur;
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
    }
}
