package future.SAE.domain.valueObject;

import java.time.LocalDateTime;

import future.SAE.domain.model.Eleve;
import future.SAE.domain.model.Section;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class EtatSection {


    private Long idEtatSection;
    private boolean estTerminee = false;
    private LocalDateTime dateCompletion;
    private Eleve eleve;
    private Section section;


    public EtatSection(Eleve unEleve, Section uneSection){
        this.eleve = unEleve;
        this.section = uneSection;
    }


    public EtatSection() {
        
    }

    public void marquerCommeTerminee() {
        if (!this.estTerminee) {
            this.estTerminee = true;
            this.dateCompletion = LocalDateTime.now();
        }
    }


}
