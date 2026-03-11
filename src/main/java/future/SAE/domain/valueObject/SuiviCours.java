package future.SAE.domain.valueObject;

import java.time.LocalDateTime;

import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Eleve;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuiviCours {


    private Long idSuiviCours;
    private Cours cours;
    private Eleve eleve;
    private float progressionGlobale;
    private LocalDateTime dateDernierAcces;

    public SuiviCours(){
    }

    public SuiviCours(Eleve unEleve, Cours unCours){
        this.eleve = unEleve;
        this.cours = unCours;
        this.dateDernierAcces = LocalDateTime.now();
    }


    public String toString() {

        return "Dernier accès au cours \"" + this.getCours() +"\" : " + this.getDateDernierAcces() + "\n" +
                "Progression Globale : " + this.getProgressionGlobale() + " %";
    }
}
