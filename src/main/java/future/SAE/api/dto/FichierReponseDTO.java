package future.SAE.api.dto;
import java.time.LocalDateTime;

import future.SAE.domain.valueObject.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FichierReponseDTO
{
    private Long id;
    private String titre;
    private String description;
    private String cheminFichier;
    private Type type;
    private Long sectionId;
    private LocalDateTime datePublication;
}
