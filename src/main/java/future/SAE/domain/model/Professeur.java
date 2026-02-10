package future.SAE.domain.model;

public class Professeur extends Utilisateur
{
    private Eleve eleve;
    private Cours cours;

    public Professeur(String unNom, String unPrenom, int id, String unEmail)
    {
        super(unNom, unPrenom, id, unEmail);
    }

    public inscriptionEleve(Eleve eleve)
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

    public desinscriptionEleve(Eleve eleve)
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
