package future.SAE.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Section {
    private Long idSection;
    private int ordre;
    private String titre;
    private String texte;
    private boolean ouverte = false;
    private Cours cours;
    private List<Fichier> fichiers = new ArrayList<>();

    // Constructeurs
    public Section() {
    }

    public Section(int unOrdre, String unTitre) {
        this.ordre = unOrdre;
        this.titre = unTitre;
    }

    public Section(int unOrdre, String unTitre, String unTexte) {
        this.ordre = unOrdre;
        this.titre = unTitre;
        this.texte = unTexte;
    }

    public Section(int unOrdre, String unTitre, String unTexte, boolean estOuverte) {
        this.ordre = unOrdre;
        this.titre = unTitre;
        this.texte = unTexte;
        this.ouverte = estOuverte;
    }

    public void fermerSection() {
        this.ouverte = false;
    }

    public void ouvrirSection() {
        this.ouverte = true;
    }
}
