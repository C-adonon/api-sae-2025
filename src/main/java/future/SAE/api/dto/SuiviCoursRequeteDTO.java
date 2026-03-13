package future.SAE.api.dto;
import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Eleve;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuiviCoursRequeteDTO
{
    private Cours cours;
    private Eleve eleve;
}
