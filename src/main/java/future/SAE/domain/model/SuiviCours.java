package future.SAE.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuiviCours {


    private Long idSuiviCours;
    private float progressionGlobale;
    private LocalDateTime dateDernierAcces;
    private Eleve eleve;


    public SuiviCours(Eleve unEleve){
        this.eleve = unEleve;
    }



    public String toString() {

        return "Dernier accès au cours : " + this.getDateDernierAcces() + "\n" +
                "Progression Globale : " + this.getProgressionGlobale() + " %";
    }
}
