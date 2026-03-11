package future.SAE.api.dto;
import future.SAE.infrastructure.persistence.CoursJPA;
import future.SAE.infrastructure.persistence.EleveJPA;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuiviCoursRequeteDTO
{
    private CoursJPA cours;
    private EleveJPA eleve;
}
