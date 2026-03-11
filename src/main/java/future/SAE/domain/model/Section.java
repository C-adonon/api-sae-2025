package future.SAE.domain.model;

public class Section {
    private int ordre;
    private String titre;
    private String texte;
    private boolean ouverte = false;

    // Constructeurs
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
        setOuverte(false);
    }

    public void ouvrirSection() {
        setOuverte(true);
    }

    // Getters
    public String getTitre() {
        return this.titre;
    }

    public int getOrdre() {
        return this.ordre;
    }

    public String getTexte() {
        return this.texte;
    }

    public boolean getOuverte() {
        return this.ouverte;
    }

    // setters
    public void setTitre(String unTitre) {
        this.titre = unTitre;
    }

    public void setOrdre(int unOrdre) {
        this.ordre = unOrdre;
    }

    public void setTexte(String unTexte) {
        this.texte = unTexte;
    }

    public void setOuverte(boolean isOuverte) {
        this.ouverte = isOuverte;
    }

    public String toString() {
        String str = "Section " + this.ordre + ": " + this.titre + "(" + this.ouverte + ")";
        if (this.texte != null) {
            str += "\n" + this.texte;
        }
        return str;
    }
}
