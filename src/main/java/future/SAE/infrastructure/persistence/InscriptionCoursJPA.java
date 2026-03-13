package future.SAE.infrastructure.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inscription_cours", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "id_cours", "id_eleve" })
})
@Getter
@Setter
public class InscriptionCoursJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscription", nullable = false, updatable = false)
    private Long idInscriptionCours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cours", nullable = false)
    private CoursJPA cours;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_eleve", nullable = false)
    private EleveJPA eleve;

    @Column(name = "date_inscription")
    private LocalDateTime dateInscription = LocalDateTime.now();

    public InscriptionCoursJPA() {
    }

}
