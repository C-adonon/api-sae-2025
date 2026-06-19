package future.SAE.domain.model;

import java.sql.Timestamp;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class EtatSection {

    private Long idEtatSection;
    private boolean estTerminee = false;
    private Timestamp dateCompletion;
    private Eleve eleve;
    private Section section;

    public EtatSection(Eleve unEleve, Section uneSection) {
        this.eleve = unEleve;
        this.section = uneSection;
    }

    public EtatSection() {

    }

    public void marquerCommeTerminee() {
        if (!this.estTerminee) {
            this.estTerminee = true;
            this.dateCompletion = new Timestamp(System.currentTimeMillis());
        }
    }

}
