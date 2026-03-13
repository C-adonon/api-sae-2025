package future.SAE.api.dto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesseurReponseDTO
{
    private String nom;
    private String prenom;
    private String email;
    private List<CoursJPA> coursDispenses;
}
