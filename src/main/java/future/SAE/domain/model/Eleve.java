package future.SAE.domain.model;
import java.util.ArrayList;
import java.util.List;

import future.SAE.domain.exception.FormationInvalideException;
import future.SAE.domain.exception.InscriptionInvalideException;
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


    public void inscriptionCours(InscriptionCours uneInscription) {
        if (uneInscription == null || uneInscription.getCours() == null) {
            throw new InscriptionInvalideException("L'inscription ou le cours ne peut pas être nul.");
        }

        boolean dejaInscrit = this.inscriptions.stream()
                .anyMatch(i -> i.getCours().equals(uneInscription.getCours()));

        if (dejaInscrit) {
            throw new InscriptionInvalideException("L'élève est déjà inscrit au cours : " + uneInscription.getCours().getNom());
        }

        this.inscriptions.add(uneInscription);
    }

    public void inscriptionFormation(Formation uneFormation) {
        if (uneFormation == null) {
            throw new FormationInvalideException("La formation ne peut pas être nulle.");
        }

        if (this.formation != null && this.formation.equals(uneFormation)) {
            throw new FormationInvalideException("L'élève est déjà inscrit à la formation : " + uneFormation.getNom());
        }

        this.formation = uneFormation;
    }

    public void supprimerFormation(Formation uneFormation) {
        this.formation = null;
    }

    public void supprimerInscription(InscriptionCours uneInscription) {
        if (uneInscription == null) {
            throw new InscriptionInvalideException("L'inscription à supprimer ne peut pas être nulle.");
        }

        if (!this.inscriptions.contains(uneInscription)) {
            throw new InscriptionInvalideException("Impossible de désinscrire : l'élève n'est pas inscrit à ce cours.");
        }

        this.inscriptions.remove(uneInscription);
    }



    public String toString()
    {
        return "Eleve " + super.toString() + " fait partie de la formation " + this.formation + " et est inscrit à " + this.inscriptions.size() + " cours.";
    }
}
