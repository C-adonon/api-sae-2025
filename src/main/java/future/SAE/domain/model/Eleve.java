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
    private List<InscriptionCours> inscriptions;

    //constructeur avec heritage
    public Eleve()
    {
        super();
    }

    public Eleve(String unNom, String unPrenom, String unIdentifiant, String unEmail, String unMdp, Formation uneFormation)
    {
        super(unNom, unPrenom, unIdentifiant, unEmail, unMdp);
        this.inscriptions = new ArrayList<>();
        this.formation = uneFormation;
    }

    public Eleve(String unNom, String unPrenom, String unIdentifiant, String unEmail, String unMdp, Formation uneFormation, List<InscriptionCours> listeInscriptionCours)
    {
        super(unNom, unPrenom, unIdentifiant, unEmail, unMdp);
        this.formation = uneFormation;
        this.inscriptions = listeInscriptionCours;
    }

    //inscriptions à une formation
    public void inscriptionFormation(Formation uneFormation)
    {
        this.formation = uneFormation;
    }

    public void supprimerFormation(Formation uneFormation)
    {
        this.formation = null;
    }

    public void inscriptionCours(InscriptionCours uneInscription)
    {
        this.inscriptions.add(uneInscription);
    }

    public void supprimerInscription(InscriptionCours uneInscription)
        {
        this.inscriptions.remove(uneInscription);
        }

    public String toString()
    {
        return "Eleve " + super.toString() + " fait partie de la formation " + this.formation + " et de la liste " + this.inscriptions;
    }
}
