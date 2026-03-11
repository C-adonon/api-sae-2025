package future.SAE.domain.model;
import java.util.ArrayList;
import java.util.List;

import future.SAE.domain.valueObject.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Eleve extends Utilisateur
{
    private Formation formation;
    private List<InscriptionCours> inscriptions = new ArrayList<>();

    //constructeur avec heritage
    public Eleve()
    {
        super();
    }

    //constructeur d'un utilisateur ayant pour role ELEVE
    public Eleve(String unNom, String unPrenom, int unIdentifiant, String unEmail, String unMdp)
    {
        super(unNom, unPrenom, unIdentifiant, unEmail, unMdp, Role.ELEVE);
    }

    //constructeur d'un utilisateur ayant pour role ELEVE qui est relie a une formation et une liste
    public Eleve(String unNom, String unPrenom, int unIdentifiant, String unEmail, String unMdp, Formation uneFormation, List<InscriptionCours> listeInscriptionCours)
    {
        super(unNom, unPrenom, unIdentifiant, unEmail, unMdp, Role.ELEVE);
        this.formation = uneFormation;
        this.inscriptions = listeInscriptionCours;
    }

    public String toString()
    {
        return "Eleve " + super.toString() + " fait partie de la formation " + this.formation + " et de la liste " + this.inscriptions;
    }
}
