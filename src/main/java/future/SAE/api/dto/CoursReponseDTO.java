package future.SAE.api.dto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class CoursReponseDTO
{
    private Long id;
    private String nom;
    private String description;
    private boolean publique;
    private Long professeurId;
    private Long formationId;
    private List<SectionReponseDTO> section;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
}
