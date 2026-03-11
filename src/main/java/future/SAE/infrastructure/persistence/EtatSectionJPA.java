package future.SAE.infrastructure.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "etat_section", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_section", "id_eleve"})
})
@Getter
@Setter
public class EtatSectionJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etat_section", nullable = false, updatable = false)
    private long idEtatSection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_eleve", nullable = false)
    private EleveJPA eleve;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_section", nullable = false)
    private SectionJPA section;

    @Column(name = "est_terminee")
    private boolean estTerminee = false;

    @Column(name = "date_completion")
    private LocalDateTime dateCompletion;

    public EtatSectionJPA(){
    }
}
