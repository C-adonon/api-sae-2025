import future.SAE.domain.model.Eleve;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestEleve
{
    @Test
    public void creerEleve()
    {
        Eleve e = new Eleve("BENDJEBBOUR", "Yasmine", 12301458, "yasmine.bendjebbour@edu.univ.fr", "Y@smine2005!");
        Assert.isTrue(e.getNom().equals("BENDJEBBOUR"), "Son nom est Bendjebbour");
        Assert.isTrue(e.getPrenom().equals("Yasmine"), "Son prénom est Yasmine");
        Assert.isTrue(e.getIdentifiant() == 12301458), "Son id est 12301458");
        Assert.isTrue(e.getEmail().equals("yasmine.bendjebbour@edu.univ.fr"), "Adresse mail valide");
    }

    @Test
    public void iscritEleve()
    {
        //On initialise une formation et une liste d'inscription
        Formation f = new Formation("Informatique");
        List<InscriptionCours> maListe = new ArrayList<>();

        //On initialise un eleve avec la formation et la liste d'inscription créée
        Eleve v = new Eleve("ADONON", "Chloe", 12512557, "chloe.adonon@edu.univ.fr", "Chl0e2000!", f, mesInscriptions);

        Assert.isTrue(v.getFormation().equals(f), "L'élève est en informatique");
        Assert.isTrue(e.getInscriptions().equals(maListe), "L'élève fais parti de la liste d'inscription");
    }
}