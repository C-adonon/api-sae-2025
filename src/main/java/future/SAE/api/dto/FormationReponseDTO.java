package future.SAE.api.dto;
import future.SAE.domain.valueObject.Semestre;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FormationReponseDTO
{
    private Long id;
    private String nom;
    private int annee;
    private Semestre semestre;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
}
