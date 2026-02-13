import future.SAE.domain.model.Eleve;
import org.junit.jupiter.api.Test;

public class TestEleve
{
    @Test
    public void TestEleve()
    {
        Eleve e = new Eleve("BENDJEBBOUR", "Yasmine", 12301458, "yasmine.bendjebbour@edu.univ.fr");
        System.out.println(e.toString());
    }
}