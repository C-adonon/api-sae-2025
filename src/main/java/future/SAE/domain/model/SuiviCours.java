package future.SAE.domain.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;


public class SuiviCours {


    private Long id;
    private float progressionGlobale;
    private LocalDateTime dateDernierAcces;


    private Eleve eleve;

    public SuiviCours(Eleve unEleve){
        this.eleve = unEleve;
    }

    public float getProgressionGlobale() {
        return progressionGlobale;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateDernierAcces() {
        return dateDernierAcces;
    }

    public void setProgressionGlobale(float progressionGlobale) {
        this.progressionGlobale = progressionGlobale;
    }

    public void setDateDernierAcces(LocalDateTime dateDernierAcces) {
        this.dateDernierAcces = dateDernierAcces;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }


    public String toString() {
        // à faire
        return "";
    }
}
