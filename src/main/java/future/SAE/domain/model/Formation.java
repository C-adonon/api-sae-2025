import java.util.ArrayList;
import java.util.List;

public class Formation {

    private int annee;
    private String nom;
    private List<Professeur> responsables;
    private Semestre semestre;
    private List<Cours> cours;
    private List<Competences> competences;

    public Formation(int annee, String nom, List<Professeur> responsables, Semestre semestre) {
        this.annee = annee;
        this.nom = nom;
        this.semestre = semestre;
        this.cours = new ArrayList<>();
        this.competences = new ArrayList<>();
        setResponsables(responsables);
    }

    // getters
    public int getAnnee() {
        return annee;
    }

    public String getNom() {
        return nom;
    }

    public List<Professeur> getResponsables() {
        return responsables;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    // setters
    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public void setResponsables(List<Professeur> responsables) {
        if (responsables == null || responsables.size() < 1 || responsables.size() > 2) {
            throw new IllegalArgumentException("Une formation doit avoir entre 1 et 2 responsables.");
        }
        this.responsables = responsables;
    }

    @Override
    public String toString() {
        return "Formation: " + nom + " (" + semestre + ")";
    }
}