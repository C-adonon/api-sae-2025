package future.SAE.api.dto;
import java.time.LocalDateTime;
import java.util.UUID;

public class UtilisateurReponseDTO
{
    @Getter
    @Setter
    public class UtilisateurResponseDTO
    {
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
