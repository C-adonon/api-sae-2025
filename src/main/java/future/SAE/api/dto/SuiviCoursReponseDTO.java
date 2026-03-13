package future.SAE.api.dto;
import java.time.LocalDateTime;
import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Eleve;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuiviCoursReponseDTO
{
    private Long id;
    private Cours cours;
    private Eleve eleve;
    private float progressionGlobale;
    private LocalDateTime dateCompletion;
}
