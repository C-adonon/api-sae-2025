package future.SAE.domain.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuiviCours {

    private Long idSuiviCours;
    private Cours cours;
    private Eleve eleve;
    private float progressionGlobale;
    private Timestamp dateDernierAcces;

    public SuiviCours() {
    }

    public SuiviCours(Eleve unEleve, Cours unCours) {
        this.eleve = unEleve;
        this.cours = unCours;
        this.dateDernierAcces = new Timestamp(System.currentTimeMillis());
    }

    public String toString() {

        return "Dernier accès au cours \"" + this.getCours() + "\" : " + this.getDateDernierAcces() + "\n" +
                "Progression Globale : " + this.getProgressionGlobale() + " %";
    }
}
