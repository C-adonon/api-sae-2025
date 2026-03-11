package future.SAE.domain.model;
import java.util.ArrayList;
import java.util.List;

import future.SAE.domain.valueObject.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Professeur extends Utilisateur
{
    private List<Cours> coursDispenses = new ArrayList<>();

    //constructeur avec héritage
    public Professeur(String unNom, String unPrenom, int unIdentifiant, String unEmail, String unMdp)
    {
        super(unNom, unPrenom, unIdentifiant, unEmail, unMdp, Role.PROFESSEUR);
    }

    public void ajouterCours(Cours unCours)
    {
        if(unCours != null)
        {
            this.coursDispenses.add(unCours);
        }
    }

    public String toString()
    {
        return "Prfesseur" + super.toString();
    }
}
