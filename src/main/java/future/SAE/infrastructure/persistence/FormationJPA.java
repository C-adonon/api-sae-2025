package future.SAE.infrastructure.persistence;

import future.SAE.domain.valueObject.Semestre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "formation")
@Getter
@Setter
public class FormationJPA {
    @Id
    @Column(name = "id_formation", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormation;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private int annee;

    @Enumerated(EnumType.STRING)
    private Semestre semestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_responsable", nullable = false)
    private ProfesseurJPA responsable;

    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompetenceJPA> competences = new ArrayList<>();

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation = LocalDateTime.now();

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    public FormationJPA() {

    }

}
