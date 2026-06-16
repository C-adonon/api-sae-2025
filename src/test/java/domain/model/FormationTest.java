package domain.model;

import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Formation;
import future.SAE.domain.model.Professeur;
import future.SAE.domain.valueObject.Semestre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FormationTest
{
    private Formation f;
    private Cours c;
    private Professeur p;
    private int a;
    private String nom;
    private Semestre s;

    @BeforeEach
    public void init()
    {
        f = new Formation(a, nom, p, Semestre.S1);
        c = new Cours("Automates", p, f);
        p = new Professeur("KHADROUF", "Issam", "16793016", "issam.khadrouf@edu.univ.fr","Iss@m2026!");
        a = 2;
        nom = "Informatique";
        s = Semestre.S2;
    }

    @Test
    public void creerFormation()
    {
        assertEquals(f.getNom(), nom);
        assertEquals(f.getAnnee(), a);
        assertEquals(f.getSemestre(), s);
        assertEquals(f.getResponsable(), p);
    }

    @Test
    public void ajouterCours()
    {
        f.ajouterCours(c);
        assertTrue(f.getCours().contains(c));
        assertEquals(2, f.getCours().size());
    }

    @Test
    public void supprimerCours()
    {
        f.ajouterCours(c);

        boolean res = f.supprimerCours(c);

        assertTrue(res);
        assertFalse(f.getCours().contains(c));
    }
}
