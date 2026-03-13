package future.SAE.api.dto;
import future.SAE.infrastructure.persistence.EleveJPA;
import future.SAE.infrastructure.persistence.SectionJPA;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EtatSectionRequeteDTO
{
    private EleveJPA eleve;
    private SectionJPA section;
    private boolean estTerminee;
}
