package future.SAE.domain.model;

import java.time.LocalDateTime;
import future.SAE.domain.valueObject.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fichier {
    private Long idFichier;
    private String titre;
    private String description;
    private String cheminFichier;
    private Type type;
    private Section section;
    private LocalDateTime datePublication = LocalDateTime.now();

    public Fichier() {
    }

    public Fichier(String titre, String cheminFichier, Section uneSection, Type type) {
        this.titre = titre;
        this.cheminFichier = cheminFichier;
        this.section = uneSection;
        this.type = type;
    }

    public Fichier(String titre, String uneDescription, String cheminFichier, Section uneSection, Type type) {
        this.titre = titre;
        this.cheminFichier = cheminFichier;
        this.section = uneSection;
        this.type = type;
        this.description = uneDescription;
    }

    @Override
    public String toString() {
        return "Fichier: " + this.titre + "." + this.type;
    }

}