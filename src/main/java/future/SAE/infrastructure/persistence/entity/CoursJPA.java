package future.SAE.infrastructure.persistence.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cours")
@Getter
@Setter
public class CoursJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cours", nullable = false, updatable = false)
    private Long idCours;

    @Column(nullable = false)
    private String nom;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private boolean publique = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professeur")
    private ProfesseurJPA professeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formation")
    private FormationJPA formation;

    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SectionJPA> sections = new ArrayList<>();

    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InscriptionCoursJPA> inscriptions = new ArrayList<>();

    @Column(name = "date_creation", nullable = false)
    private Timestamp dateCreation = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "date_modification")
    private Timestamp dateModification;

    public CoursJPA() {
    }
}
