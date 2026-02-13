import java.time.LocalDateTime;

public abstract class Fichier {

    private String titre;
    private String description;
    private String cheminFichier;
    private LocalDateTime datePublication;
    private Type type;

    public Fichier(String titre, String cheminFichier, Type type) {
        this.titre = titre;
        this.cheminFichier = cheminFichier;
        this.type = type;
        this.datePublication = LocalDateTime.now();
    }

    public Fichier(String titre, String description, String cheminFichier, Type type) {
        this.titre = titre;
        this.cheminFichier = cheminFichier;
        this.type = type;
        this.datePublication = LocalDateTime.now();
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public LocalDateTime getDatePublication() {
        return datePublication;
    }

    public Type getType() {
        return type;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCheminFichier(String chemin) {
        this.cheminFichier = chemin;
    }

    @Override
    public String toString() {
        return "Fichier : " + titre + " [" + type + "]";
    }
}