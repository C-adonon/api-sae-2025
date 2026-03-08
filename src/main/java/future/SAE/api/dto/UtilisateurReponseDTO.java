package future.SAE.api.dto;


public class UtilisateurReponseDTO
{
    import java.time.LocalDateTime;
import java.util.UUID;

    @Getter
    @Setter
    public class UtilisateurResponseDTO {

        private UUID id;
        private int identifiant;
        private String nom;
        private String prenom;
        private String email;
        private Role role;
        private UUID formationId;
        private LocalDateTime dateCreation;

    }
}
