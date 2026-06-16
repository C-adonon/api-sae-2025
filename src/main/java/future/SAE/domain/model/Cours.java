package future.SAE.domain.model;

import java.security.Timestamp;
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
    private Timestamp dateCreation;
    private Timestamp dateModification;

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

    public boolean supprimerSection(Section uneSection)
    {
        return this.sections.remove(uneSection);
    }

    public void ajouterSection(Section uneSection)
    {
        if(uneSection == null)
        {
            throw new SectionInvalideExeption();
        }
        for (Section s : this.sections)
        {
            if(s.getOrdre() == uneSection.getOrdre())
            {
                throw new OrdreSectionExistantException(uneSection.getOrdre());
            }
        }
        this.sections.add(uneSection);
        uneSection.setCours(this);
    }

    public boolean ouvrirSection(Section uneSection)
    {
        return uneSection.ouvrirSection();
    }

    public String toString()
    {
        return "Le cours de " + this.nom + " est dispensé par " + this.professeur + " dans la formation " + this.formation ;
    }
}