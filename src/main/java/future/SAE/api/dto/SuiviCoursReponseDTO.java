package future.SAE.api.dto;
import future.SAE.infrastructure.persistence.CoursJPA;
import future.SAE.infrastructure.persistence.EleveJPA;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuiviCoursReponseDTO
{
    private Long id;
    private CoursJPA cours;
    private EleveJPA eleve;
    private float progressionGlobale;
    private LocalDateTime dateCompletion;
}
