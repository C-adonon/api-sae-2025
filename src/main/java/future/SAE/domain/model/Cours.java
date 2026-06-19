package future.SAE.domain.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import future.SAE.domain.exception.OrdreSectionExistantException;
import future.SAE.domain.exception.SectionInvalideException;
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
    private List<Eleve> inscriptions = new ArrayList<>();

    private Timestamp dateCreation;
    private Timestamp dateModification;

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

    public Cours(String unNom, String description, Professeur unProfesseur, boolean isPublique, Formation uneFormation) {
        this.nom = unNom;
        this.description = description;
        this.professeur = unProfesseur;
        this.formation = uneFormation;
        this.publique = isPublique;
    }

    public void ajouterSection(Section uneSection) {
        if(uneSection == null) {
            throw new SectionInvalideException();
        }
        for (Section s : this.sections) {
            if(s.getOrdre() == uneSection.getOrdre()) {
                throw new OrdreSectionExistantException(uneSection.getOrdre());
            }
        }
        this.sections.add(uneSection);
        uneSection.setCours(this);
    }

    public boolean supprimerSection(Section uneSection) {
        if(uneSection == null) {
            return false;
        }
        boolean supprimee = this.sections.remove(uneSection);
        if(supprimee) {
            uneSection.setCours(null);
        }
        return supprimee;
    }

    public boolean ouvrirSection(Section uneSection) {
        return uneSection.ouvrirSection();
    }

    public String toString() {
        return "Le cours de " + this.nom + " est dispensé par " + this.professeur + " dans la formation " + this.formation ;
    }
}