package future.SAE.domain.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import future.SAE.domain.valueObject.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fichier {
    private Long id;
    private String titre;
    private String description;
    private String cheminFichier;
    private Type type;
    private Section section;
    private Timestamp datePublication;

    public Fichier() {
    }

    public Fichier(String titre, String cheminFichier, Section uneSection, Type type) {
        this.titre = titre;
        this.cheminFichier = cheminFichier;
        this.section = uneSection;
        this.type = type;
        this.datePublication = Timestamp.valueOf(LocalDateTime.now());
    }

    public Fichier(String titre, String uneDescription, String cheminFichier, Section uneSection, Type type) {
        this.titre = titre;
        this.cheminFichier = cheminFichier;
        this.section = uneSection;
        this.type = type;
        this.description = uneDescription;
        this.datePublication = Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Fichier: " + this.titre + "." + this.type;
    }

}