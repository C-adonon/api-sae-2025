package future.SAE.api.dto;
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
    private Long formationId;
}
