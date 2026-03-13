package future.SAE.api.dto;
import java.util.UUID;
import java.util.List;

import future.SAE.domain.model.InscriptionCours;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EleveReponseDTO
{
    private String nom;
    private String prenom;
    private String email;
    private UUID formationId;
    private List<InscriptionCours> inscriptions;
}
