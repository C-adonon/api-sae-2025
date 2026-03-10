package future.SAE.api.dto;
import java.util.UUID;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurReponseDTO
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
