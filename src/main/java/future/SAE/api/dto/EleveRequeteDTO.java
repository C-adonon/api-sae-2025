package future.SAE.api.dto;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EleveRequeteDTO
{
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private UUID formationId;
}
