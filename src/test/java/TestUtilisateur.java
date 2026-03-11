import future.SAE.domain.model.Utilisateur;
import org.junit.jupiter.api.Test;
public class TestUtilisateur
{
    @Test
    public void TestUtilisateur()
    {
        Utilisateur u = new Utilisateur("BENDJEBBOUR", "Yasmine", 12301458, "yasmine.bendjebbour@edu.univ-paris13.fr", "Y@smine2005.", Role.ELEVE);
        Utilisateur u1 = new Utilisateur("ADONON", "Chloe", 12401459, "chloe.adonon@edu.univ-paris13.fr", "Chl0e2000.");
        System.out.println(u);
        System.out.println(u1);
        System.out.println(u.toString());



    }


}



