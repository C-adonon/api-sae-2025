package future.SAE.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private List<Competence> competences = new ArrayList<>();
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

    @Override
    public String toString() {
        return "Formation: " + this.nom + " (" + this.semestre + ")";
    }
}
