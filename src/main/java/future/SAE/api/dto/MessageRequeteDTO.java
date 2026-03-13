package future.SAE.api.dto;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequeteDTO
{
    private String objet;
    private String message;
    private UUID expediteurId;
    private List<UUID> destinatairesIds;
    private UUID messageParentId;
}
