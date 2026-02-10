package future.SAE.domain.model;

import java.util.Date;

public class Cours {
    private String nom;
    private String description;
    private Professeur professeur;
    private Section[] sections;
    private boolean publique = true;
    private Date dateCreation = new Date();
    private Date dateModification;

    // Constructeyrs
    public Cours(String unNom, Professeur unProfesseur) {
        this.nom = unNom;
        this.professeur = unProfesseur;
    }

    public Cours(String unNom, Professeur unProfesseur, boolean isPublique) {
        this.nom = unNom;
        this.professeur = unProfesseur;
        this.publique = isPublique;
    }

    public Cours(String unNom, String description, Professeur unProfesseur, boolean isPublique) {
        this.nom = unNom;
        this.description = description;
        this.professeur = unProfesseur;
        this.publique = isPublique;
    }

    // Getters
    public String getNom() {
        return this.nom;
    }

    public String getDescription() {
        return this.description;
    }

    public Professeur getProfesseur() {
        return this.professeur;
    }

    public Section[] getSections() {
        return this.sections;
    }

    public boolean isPublique() {
        return this.publique;
    }

    public Date getDatecreation() {
        return this.dateCreation;
    }

    public Date getDateModification() {
        return this.dateModification;
    }

    // Setters
    public void setNom(String unNom) {
        this.nom = unNom;
    }

    public void setDescription(String uneDescription) {
        this.description = uneDescription;
    }

    public void setProfesseur(Professeur unProfesseur) {
        this.professeur = unProfesseur;
    }

    public void setSections(Section[] sectionsListe) {
        this.sections = sectionsListe;
    }

    public void setPublique(boolean isPublique) {
        this.publique = isPublique;
    }

    public void setDateCreation(Date uneDateCreation) {
        this.dateCreation = uneDateCreation;
    }

    public void setDateModification(Date uneDateModification) {
        this.dateModification = uneDateModification;
    }
}