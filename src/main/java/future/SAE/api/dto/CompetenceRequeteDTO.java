package future.SAE.api.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetenceRequeteDTO
{
    private int numero;
    private String libelle;
    private String description;
    private int formationID;
}
