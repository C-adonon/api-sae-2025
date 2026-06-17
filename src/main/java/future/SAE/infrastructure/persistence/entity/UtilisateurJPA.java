package future.SAE.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "utilisateur")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Une seule table pour tout le monde
@DiscriminatorColumn(
        name = "type_utilisateur",
        discriminatorType = DiscriminatorType.STRING,
        columnDefinition = "VARCHAR(255)"
) // La colonne qui dira si c'est un prof ou un élève

@Getter
@Setter
@NoArgsConstructor
public abstract class UtilisateurJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_user")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String identifiant;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    @Column(name = "date_creation", nullable = false)
    private Timestamp dateCreation;
}