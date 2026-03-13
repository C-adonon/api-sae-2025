package future.SAE.api.dto;
import future.SAE.domain.model.Eleve;
import future.SAE.domain.model.Section;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EtatSectionReponseDTO
{
    private Long id;
    private Eleve eleve;
    private Section section;
    private boolean estTerminee;
    private LocalDateTime dateCompletion;
}
