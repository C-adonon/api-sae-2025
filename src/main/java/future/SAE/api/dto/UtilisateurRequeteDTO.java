package future.SAE.api.dto;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurRequeteDTO
{
    private int identifiant;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Role role;
    private UUID formationId;

}
