package future.SAE.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Competence")
@Getter
@Setter

public class CompetenceJPA {
    @Id
    @Column(name = "id_competence", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCompetence;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false)
    private String libelle;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formation", nullable = false)
    private FormationJPA formation;

    public CompetenceJPA(){
    }

}
