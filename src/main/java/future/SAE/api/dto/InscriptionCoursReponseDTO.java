package future.SAE.api.dto;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscriptionCoursReponseDTO
{
    private Long idInscription;
    private Long coursId;
    private UUID eleveId;
    private LocalDateTime dateInscription;
}
