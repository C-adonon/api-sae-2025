package future.SAE.api.dto;
import future.SAE.infrastructure.persistence.EleveJPA;
import future.SAE.infrastructure.persistence.SectionJPA;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EtatSectionReponseDTO
{
    private Long id;
    private EleveJPA eleve;
    private SectionJPA section;
    private boolean estTerminee;
    private LocalDateTime dateCompletion;
}
