package future.SAE.infrastructure.persistence.entity;

import future.SAE.domain.valueObject.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "date_publication")
    private java.sql.Timestamp date_publication = java.sql.Timestamp.from(java.time.Instant.now());

    public FichierJPA() {
    }

}
