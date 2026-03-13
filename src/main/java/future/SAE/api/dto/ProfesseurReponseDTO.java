package future.SAE.api.dto;
import java.util.List;

import future.SAE.domain.model.Cours;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesseurReponseDTO
{
    private String nom;
    private String prenom;
    private String email;
    private List<Cours> coursDispenses;
}
