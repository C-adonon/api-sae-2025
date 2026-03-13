package future.SAE.api.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetenceReponseDTO
{
    private int id;
    private int numero;
    private String libelle;
    private String description;
    private int formationID;
}
