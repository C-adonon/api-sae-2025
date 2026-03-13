package future.SAE.infrastructure.persistence;

import java.time.LocalDateTime;
import java.util.UUID;

import future.SAE.domain.valueObject.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@jakarta.persistence.Table(name = "utilisateur")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public class UtilisateurJPA {
    @Id
    @Column(name = "id_user", columnDefinition = "uuid", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUser;

    @Column(nullable = false, unique = true)
    private int identifiant;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, insertable = false, updatable = false)
    private Role role;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation = LocalDateTime.now();

    public UtilisateurJPA() {

    }
}


