package future.SAE.infrastructure.persistence;

import future.SAE.domain.valueObject.Semestre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "formation")
@Getter
@Setter
public class FormationJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_formation")
    private Long id;

    @Column(nullable = false)
    private String nom;

    private int annee;

    @Enumerated(EnumType.STRING)
    private Semestre semestre;

    @ManyToOne
    @JoinColumn(name = "id_responsable")
    private UtilisateurJPA responsable;

    @ManyToMany
    @JoinTable(name = "formation_competence", joinColumns = @JoinColumn(name = "id_formation"), inverseJoinColumns = @JoinColumn(name = "id_competence"))
    private List<CompetenceJPA> competences = new ArrayList<>();

    public FormationJPA() {

    }

}
