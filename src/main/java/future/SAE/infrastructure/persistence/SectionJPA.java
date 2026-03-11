package future.SAE.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "section")
@Getter
@Setter
public class SectionJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_section", nullable = false, updatable = false)
    private Long idSection;

    @Column(nullable = false)
    private int ordre;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String texte;

    @Column(nullable = false)
    private boolean ouverte = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cours", nullable = false)
    private CoursJPA cours;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FichierJPA> fichiers = new ArrayList<>();

    public SectionJPA() {
    }
}
