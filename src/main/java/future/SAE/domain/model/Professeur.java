package future.SAE.domain.model;
import java.util.ArrayList;
import java.util.List;

import future.SAE.domain.exception.CoursDejaAssigneException;
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
    public Professeur(String unNom, String unPrenom, String unIdentifiant, String unEmail, String unMdp)
    {
        super(unNom, unPrenom, unIdentifiant, unEmail, unMdp);
    }

    public void ajouterCoursDispense(Cours unCours)
    {
        if(unCours == null)
            return;

        if (unCours.getProfesseur() != null && !unCours.getProfesseur().equals(this)) {
            throw new CoursDejaAssigneException(unCours.getNom());
        }
        
        this.coursDispenses.add(unCours);
    }

    public void supprimerCoursDispense(Cours unCours)
    {
        if (unCours == null) {
            return;
        }

        // On vérifie si le cours est bien dispensé par ce prof (soit par la liste, soit par la référence)
        if (!this.coursDispenses.contains(unCours) || (unCours.getProfesseur() != null && !unCours.getProfesseur().equals(this))) {
            throw new CoursDejaAssigneException(unCours.getNom());
        }

        this.coursDispenses.remove(unCours);
        if (unCours.getProfesseur() == this) {
            unCours.setProfesseur(null);
        }
    }

    public String toString()
    {
        return "Professeur" + "\n" +super.toString();
    }

    public void nommerResponsableDe(Formation uneFormation){
        this.formationSupervisee = uneFormation;
        if (uneFormation != null && uneFormation.getResponsable() != this) {
            uneFormation.setResponsable(this);
        }
    }
}
