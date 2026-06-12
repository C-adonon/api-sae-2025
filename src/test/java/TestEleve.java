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
        Assert.isTrue(e.getIdentifiant().equals("12301458"), "Son id est 12301458");
    }
}