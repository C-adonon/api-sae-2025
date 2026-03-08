package future.SAE.domain.model;
import java.util.ArrayList;
import java.util.List;
// import java.util.ArrayList;
// import future.SAE.domain.valueObject.InscriptionCours;
import future.SAE.domain.valueObject.Role;
// pas besoin de lier les eleves au prof
// pas de methodes inscriptionEleve pour le moment
public class Professeur extends Utilisateur
{
    private List<Cours> coursDispenses = new ArrayList<>();

    public Professeur(String unNom, String unPrenom, int identifiant, String unEmail, String unMdp)
    {
        super(unNom, unPrenom, identifiant, unEmail, unMdp, Role.PROFESSEUR);
    }

    // public boolean inscriptionEleve(Eleve unEleve, Cours unCours) {
    // if (unEleve != null && unCours != null) {
    // InscriptionCours inscription = new InscriptionCours(unCours, unEleve);
    // return true;
    // } else {
    // return false;
    // }
    // // if (this.cours.add(eleve))
    // // {
    // // return true;
    // // }
    // // else
    // // {
    // // return false;
    // // }
    // }

    // public boolean desinscriptionEleve(Eleve eleve, Cours unCours)
    // {
    // // if (this.cours.remove(eleve))
    // // {
    // // return true;
    // // }
    // // else
    // // {
    // // return false;
    // // }
    // }

    public List<Cours> getCours() {
        return this.coursDispenses;
    }

    public void ajouterCours(Cours unCours)
    {
        if (unCours != null)
        {
            this.coursDispenses.add(unCours);
        }
    }
    // public ArrayList<Formation> getFormations()
    // {
    //      return this.formations;
    // }

    // public void ajouterFormation(Formation formation)
    // {
    //      if (formation != null)
    //      {
    //          this.formations.add(formation);
    //      }
    // }

    public String toString()
    {
        return "Professeur" + super.toString();
    }
}
