package future.SAE.api.dto;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class CoursRequeteDTO
{
    private String nom;
    private String description;
    private boolean publique;
    private Long professeurId;
    private Long formationId;
}
