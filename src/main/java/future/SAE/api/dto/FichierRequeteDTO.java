package future.SAE.api.dto;
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
