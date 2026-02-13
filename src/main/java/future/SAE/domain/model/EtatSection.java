package future.SAE.domain.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class EtatSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean estTerminee = false;
    private LocalDateTime dateCompletion;

    @ManyToOne
    private Eleve eleve;
    @ManyToOne
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
