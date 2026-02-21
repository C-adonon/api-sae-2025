package future.SAE.domain.model;
import java.util.ArrayList;

public class Professeur extends Utilisateur
{
    private Eleve eleve;
    private ArrayList <Cours> cours;

    public Professeur(String unNom, String unPrenom, int id, String unEmail)
    {
        super(unNom, unPrenom, id, unEmail);
    }

    public boolean inscriptionEleve(Eleve eleve)
    {
        if (this.cours.add(eleve))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean desinscriptionEleve(Eleve eleve)
    {
        if (this.cours.remove(eleve))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        return "Professeur" +super.toString();
    }
}
