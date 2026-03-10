package future.SAE.infrastructure.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "suivi_cours", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_cours", "id_eleve"})
})
@Getter
@Setter
public class SuiviCoursJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suivi_cours", nullable = false)
    private Long idSuiviCours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cours")
    private CoursJPA cours;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_eleve", nullable = false)
    private EleveJPA eleve;

    @Column(name = "progression_globale")
    private float progressionGlobale;

    @Column(name = "dateDernierAcces")
    private LocalDateTime dateDernierAcces = LocalDateTime.now();

    public SuiviCoursJPA(){
    }

}

