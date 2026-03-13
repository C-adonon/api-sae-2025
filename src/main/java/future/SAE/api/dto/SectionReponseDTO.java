package future.SAE.api.dto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionReponseDTO
{
    private Long id;
    private int ordre;
    private String titre;
    private String texte;
    private boolean ouverte;
    private Long coursId;
    private List<FichierReponseDTO> fichiers;
}
