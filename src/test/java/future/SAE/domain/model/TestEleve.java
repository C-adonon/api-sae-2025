package future.SAE.domain.model;

import future.SAE.domain.model.Eleve;
import future.SAE.domain.model.Formation;
import future.SAE.domain.model.InscriptionCours;
import future.SAE.domain.valueObject.Semestre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class TestEleve {

    Eleve e;
    Formation f;

    @BeforeEach
    public void setUp() {
        f = new Formation(1, "Informatique", null, Semestre.S1);
        e = new Eleve("BENDJEBBOUR", "Yasmine", "12301458", "yasmine.bendjebbour@edu.univ.fr", "Y@smine2005!", f);
    }
    @Test
    public void creerEleve()
    {
        Assert.isTrue(e.getNom().equals("BENDJEBBOUR"), "Son nom est Bendjebbour");
        Assert.isTrue(e.getPrenom().equals("Yasmine"), "Son prénom est Yasmine");
        Assert.isTrue(e.getIdentifiant().equals("12301458"), "Son id est 12301458");
        Assert.isTrue(e.getEmail().equals("yasmine.bendjebbour@edu.univ.fr"), "Adresse mail valide");
    }

    @Test
    public void inscritEleve()
    {
        //On initialise une formation et une liste d'inscriptions
        List<InscriptionCours> maListe = new ArrayList<>();

        //On initialise un eleve avec la formation et la liste d'inscriptions créée
        Eleve v = new Eleve("ADONON", "Chloe", "12512557", "chloe.adonon@edu.univ.fr", "Chl0e2000!", f);

        Assert.isTrue(v.getFormation().equals(f), "L'élève est en informatique");
        Assert.isTrue(e.getInscriptions().equals(maListe), "L'élève fais parti de la liste d'inscriptions");
    }
}