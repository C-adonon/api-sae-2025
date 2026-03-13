package future.SAE.api.dto;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class MessageReponseDTO
{
    private UUID id;
    private String objet;
    private String message;
    private UUID expediteurId;
    private List<UUID> destinataireIds;
    private List<MessageReponseDTO> commentaire;
    private LocalDateTime date;
}
