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


    public Eleve()
    {
        super();
    }

    public Eleve(String unNom, String unPrenom, String unIdentifiant, String unEmail, String unMdp)
    {
        super(unNom, unPrenom, unIdentifiant, unEmail, unMdp);
        this.inscriptions = new ArrayList<>();
    }

    public Eleve(String unNom, String unPrenom, String unIdentifiant, String unEmail, String unMdp, Formation uneFormation, List<InscriptionCours> listeInscriptionCours)
    {
        super(unNom, unPrenom, unIdentifiant, unEmail, unMdp);
        this.formation = uneFormation;
        this.inscriptions = listeInscriptionCours;
    }


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
        return "Eleve " + super.toString() + " fait partie de la formation " + this.formation + " et est inscrit à " + this.inscriptions.size() + " cours.";
    }
}
