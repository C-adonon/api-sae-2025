package future.SAE.infrastructure.persistence;

import future.SAE.domain.valueObject.Semestre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fichier")
@Getter
@Setter
public class FichierJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fichier", nullable = false, updatable = false)
    private Long idFichier;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "chemin_fichier", nullable = false)
    private String cheminFichier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_section", nullable = false)
    private SectionJPA section;

    @Column(name = "date_publication", nullable = false)
    private LocalDateTime datePublication = LocalDateTime.now();

    public FichierJPA() {
    }

}
