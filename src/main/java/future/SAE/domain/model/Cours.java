package future.SAE.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cours {
    private Long idCours;
    private String nom;
    private String description;
    private boolean publique = true;
    private Professeur professeur;
    private Formation formation;
    private List<Section> sections = new ArrayList<>();
    private List<Section> inscriptions = new ArrayList<>();
    private LocalDateTime dateCreation = LocalDateTime.now();
    private LocalDateTime dateModification;

    // Constructeurs
    public Cours() {
    }

    public Cours(String unNom, Professeur unProfesseur, Formation uneFormation) {
        this.nom = unNom;
        this.professeur = unProfesseur;
        this.formation = uneFormation;
    }

    public Cours(String unNom, Professeur unProfesseur, boolean isPublique, Formation uneFormation) {
        this.nom = unNom;
        this.professeur = unProfesseur;
        this.formation = uneFormation;
        this.publique = isPublique;
    }

    public Cours(String unNom, String description, Professeur unProfesseur, boolean isPublique,
            Formation uneFormation) {
        this.nom = unNom;
        this.description = description;
        this.professeur = unProfesseur;
        this.formation = uneFormation;
        this.publique = isPublique;
    }

    public boolean supprimerSection(Section uneSection) {
        return this.sections.remove(uneSection);
    }

    public boolean ajouterSection(Section uneSection) {
        return this.sections.add(uneSection);
    }

    public void setDateModification() {
        this.dateModification = LocalDateTime.now();
    }
}