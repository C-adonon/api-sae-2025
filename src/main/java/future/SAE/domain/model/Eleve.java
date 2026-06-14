package future.SAE.domain.model;
import java.util.ArrayList;
import java.util.List;

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

    public Eleve(String unNom, String unPrenom, int unIdentifiant, String unEmail, String unMdp)
    {
        super(unNom, unPrenom, unIdentifiant, unEmail, unMdp);
    }

    public Eleve(String unNom, String unPrenom, int unIdentifiant, String unEmail, String unMdp, Formation uneFormation, List<InscriptionCours> listeInscriptionCours)
    {
        super(unNom, unPrenom, unIdentifiant, unEmail, unMdp);
        this.formation = uneFormation;
        this.inscriptions = listeInscriptionCours;
    }

    public String toString()
    {
        return "Eleve " + super.toString() + " fait partie de la formation " + this.formation + " et de la liste " + this.inscriptions;
    }
}
