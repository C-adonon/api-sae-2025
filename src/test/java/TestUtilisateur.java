import future.SAE.domain.model.Utilisateur;
import org.junit.jupiter.api.Test;
public class TestUtilisateur
{
    @Test
    public void TestUtilisateur()
    {
        Utilisateur u = new Utilisateur("BENDJEBBOUR", "Yasmine", 12301458, "yasmine.bendjebbour@edu.univ-paris13.fr");
        System.out.println(u.toString());
        System.out.println(u.connexion(12301458, "yasmine93@"));
    }


}



