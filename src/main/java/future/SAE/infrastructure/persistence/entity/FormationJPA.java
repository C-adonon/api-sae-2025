package future.SAE.infrastructure.persistence.entity;

import future.SAE.domain.valueObject.Semestre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    private ProfesseurJPA responsable;

    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompetenceJPA> competences = new ArrayList<>();

    @Column(name = "date_creation", nullable = false)
    private Timestamp dateCreation = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "date_modification")
    private Timestamp dateModification;

    public FormationJPA() {

    }

}
