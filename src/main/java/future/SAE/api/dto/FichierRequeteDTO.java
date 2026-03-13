package future.SAE.api.dto;
import future.SAE.domain.valueObject.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FichierRequeteDTO
{
    private String titre;
    private String description;
    private String cheminFichier;
    private Type type;
    private Long sectionId;
}
