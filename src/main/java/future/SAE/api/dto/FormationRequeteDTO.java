package future.SAE.api.dto;
import future.SAE.domain.valueObject.Semestre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormationRequeteDTO
{
    private String nom;
    private int annee;
    private Semestre semestre;
}
