package future.SAE.infrastructure.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cours")
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

    private boolean publique = true;

    @ManyToOne
    @JoinColumn(name = "id_professeur", nullable = false)
    private ProfesseurJPA professeur;

    @ManyToOne
    @JoinColumn(name = "id_professeur", nullable = false)
    private FormationJPA formation;

    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL, orphanRemoval=true)
    @OrderBy("ordre ASC")
    private List<SectionJPA> sections = new ArrayList<>();

    @OneToMany(mappedBy ="cours")
    private List<InscriptionCoursJPA> inscriptions = new ArrayList<>();

    @Column(name= "date_creation")
    private LocalDateTime dateCreation = LocalDateTime.now();
    
    @Column(name= "date_modification")
    private LocalDateTime dateModification;

    public CoursJPA(){}

}
