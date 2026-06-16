package domain.model;

import future.SAE.domain.exception.OrdreSectionExistantException;
import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Formation;
import future.SAE.domain.model.Professeur;
import future.SAE.domain.model.Section;
import future.SAE.domain.valueObject.Semestre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoursTest
{
    private String nom;
    private Professeur p;
    private Formation f;
    private Cours c;

    @BeforeEach
    public void init()
    {
        nom = "Reseau";
        p = new Professeur("EDOH-DAGNON", "Clarence", "14503586", "clarence.edohdagnon@edu.univ.fr","Cl@rence2006!");
        f = new Formation(1, "Informatique", p, Semestre.S1);
        c = new Cours(nom, p, f);
    }

    @Test
    public void creerCours()
    {
        assertEquals(c.getNom(), nom);
        assertEquals(c.getProfesseur(), p);
        assertEquals(c.getFormation(), f);
    }

    @Test
    public void ajouterSectionCours()
    {
        Section s = new Section(1,"Initiation reseau");
        c.ajouterSection(s);
        assertTrue(c.getSections().contains(s));
        assertEquals(1, c.getSections().size());
    }

    @Test
    public void sectionMemeOrdre()
    {
        Section s1 = new Section(1,"Table de routage");
        Section s2 = new Section(1,"Adresse IP");
        c.ajouterSection(s1);

        try{
            c.ajouterSection(s2);
            fail("Une exception doit être levé");
        }
        catch(OrdreSectionExistantException e)
        {
        }
    }

    @Test
    public void supprimerSection()
    {
        Section s = new Section(2, "Introduction");
        c.ajouterSection(s);

        boolean res = c.supprimerSection(s);

        assertTrue(res);
        assertFalse(c.getSections().contains(s));
    }
}