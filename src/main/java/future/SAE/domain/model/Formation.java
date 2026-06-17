package future.SAE.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import future.SAE.domain.exception.CoursDejaPresentException;
import future.SAE.domain.valueObject.Semestre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Formation {

    private Long idFormation;
    private String nom;
    private int annee;
    private Semestre semestre;
    private Professeur responsable;
    private List<Cours> cours = new ArrayList<>();
    private LocalDateTime dateCreation = LocalDateTime.now();
    private LocalDateTime dateModification;

    public Formation() {

    }

    public Formation(int annee, String nom, Professeur unResponsable, Semestre semestre) {
        this.annee = annee;
        this.nom = nom;
        this.semestre = semestre;
        this.responsable = unResponsable;
    }

    public void ajouterCours(Cours unCours)
    {
        if(unCours == null)
        {
            throw new IllegalArgumentException("Impossible d'ajouter un cours soit vide");
        }
        if(this.cours.contains(unCours))
        {
            throw new CoursDejaPresentException(unCours.getNom());
        }
        this.cours.add(unCours);
        unCours.setFormation(this);
    }

    public boolean supprimerCours(Cours unCours)
    {
        if(unCours == null)
        {
            return false;
        }
        boolean supprime = this.cours.remove(unCours);
        if(supprime)
        {
            unCours.setFormation(null);
        }
        return supprime;
    }

    @Override
    public String toString() {
        return "Formation: " + this.nom + " (" + this.semestre + ")";
    }
}
