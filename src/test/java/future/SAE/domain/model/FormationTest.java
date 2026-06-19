package future.SAE.domain.model;

import future.SAE.domain.valueObject.Semestre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FormationTest {

    private Formation f;
    private Cours c;
    private Professeur p;
    private int a;
    private String nom;
    private Semestre s;

    @BeforeEach
    public void init() {
        // 1. On initialise les données de base D'ABORD
        a = 2;
        nom = "Informatique";
        s = Semestre.S2;
        p = new Professeur("KHADROUF", "Issam", "16793016", "issam.khadrouf@edu.univ.fr", "Iss@m2026!");

        // 2. On instancie la formation avec les données qui sont maintenant remplies
        f = new Formation(a, nom, p, s);

        // 3. On crée le cours (qui a besoin de p et f)
        c = new Cours("Automates", p, f);
    }

    @Test
    public void creerFormation() {
        // La norme JUnit : assertEquals(ValeurAttendue, ValeurReelle)
        assertEquals(nom, f.getNom());
        assertEquals(a, f.getAnnee());
        assertEquals(s, f.getSemestre());
        assertEquals(p, f.getResponsable());
    }

    @Test
    public void ajouterCours() {
        f.ajouterCours(c);
        assertTrue(f.getCours().contains(c));
        assertEquals(1, f.getCours().size());
    }

    @Test
    public void supprimerCours() {
        f.ajouterCours(c);

        boolean res = f.supprimerCours(c);

        assertTrue(res);
        assertFalse(f.getCours().contains(c));
    }
}