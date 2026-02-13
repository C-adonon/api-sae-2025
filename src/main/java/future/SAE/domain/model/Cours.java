package future.SAE.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cours {
    private String nom;
    private String description;
    private Professeur professeur;
    private ArrayList<Section> sections;
    private boolean publique = true;
    private LocalDate dateCreation = LocalDate.now();
    private LocalDate dateModification;

    // Constructeurs
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

    public boolean supprimerSection(Section uneSection){
       return this.sections.remove(uneSection);
    }

    public boolean ajouterSection(Section uneSection){
        return this.sections.add(uneSection);
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

    public ArrayList<Section> getSections() {
        return this.sections;
    }

    public boolean isPublique() {
        return this.publique;
    }

    public LocalDate getDateCreation() {
        return this.dateCreation;
    }

    public LocalDate getDateModification() {
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

    public void setSections(ArrayList<Section> sectionsListe) {
        this.sections = sectionsListe;
    }

    public void setPublique(boolean isPublique) {
        this.publique = isPublique;
    }

    public void setDateCreation(LocalDate uneDateCreation) {
        this.dateCreation = uneDateCreation;
    }

    public void setDateModification(LocalDate uneDateModification) {
        this.dateModification = uneDateModification;
    }
}