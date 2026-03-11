import future.SAE.domain.model.Professeur;
import future.SAE.domain.model.Eleve;
import org.junit.jupiter.api.Test;

public class TestProfesseur
{
    @Test
    public void TestProfesseur()
    {
        Cours c = new Cours("informatique");
        Professeur p = new Professeur("DUPOND", "David", 24882357, "david.dupond@lipn.fr", "D@vid1994.");
        System.out.println(p.toString());
        System.out.println(p);
        System.out.println(p.ajouterCours(c));
    }
}
