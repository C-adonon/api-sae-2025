package future.SAE.api.dto;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscriptionCoursReponseDTO
{
    private Long idInscription;
    private Long coursId;
    private Long eleveId;
    private LocalDateTime dateInscription;
}
