package future.SAE.api.dto;
import future.SAE.domain.model.Eleve;
import future.SAE.domain.model.Section;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EtatSectionRequeteDTO
{
    private Eleve eleve;
    private Section section;
    private boolean estTerminee;
}
