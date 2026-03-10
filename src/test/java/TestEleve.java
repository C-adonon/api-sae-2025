import future.SAE.domain.model.Eleve;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestEleve
{
    @Test
    public void TestEleve()
    {
        Eleve e = new Eleve("BENDJEBBOUR", "Yasmine", 12301458, "yasmine.bendjebbour@edu.univ.fr", "Y@smine2005");
        System.out.println(e.toString());

        Formation f = new Formation("informatique");

        List<InscriptionCours> listeInscriptions = new ArrayList<>();
        InscriptionCours i = new InscriptionCours("bd");
        InscriptionCours i1 = new InscriptionCours("flask");

        listeInscriptions.add(i);
        listeInscriptions.add(i1);

        Eleve e1 = new Eleve("ADONON", "Chloe", 12401659, "chloe.adonon@edu.univ.fr", "Chl0e2000", f, listeInscriptions );

        System.out.println(e);
        System.out.println(e1);
    }
}