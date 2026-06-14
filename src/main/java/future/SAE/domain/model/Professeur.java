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
    private Formation formationSupervisee;
    private List<Cours> coursDispenses = new ArrayList<>();

    //constructeur vide
    public Professeur()
    {
        super();
    }

    //constructeur avec héritage
    public Professeur(String unNom, String unPrenom, int unIdentifiant, String unEmail, String unMdp)
    {
        super(unNom, unPrenom, unIdentifiant, unEmail, unMdp, Role.PROFESSEUR);
    }

    public void ajouterCoursDispense(Cours unCours)
    {
        if(unCours == null)
            return;

        if (unCours.getProfesseur() != null && !unCours.getProfesseur().equals(this)) {
            throw new IllegalArgumentException("Impossible d'ajouter ce cours : il est dispensé par un autre professeur.");
        }
        
        this.coursDispenses.add(unCours);
    }

    public String toString()
    {
        return "Professeur" + super.toString();
    }

    public void nommerResponsableDe(Formation uneFormation){
        this.formationSupervisee = uneFormation;
        if (uneFormation != null && uneFormation.getResponsable() != this) {
            uneFormation.setResponsable(this);
        }
    }
}
