package future.SAE.api.dto;
import java.util.UUID;

import future.SAE.domain.valueObject.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurRequeteDTO
{
    private UUID id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Role role;
    private UUID formationId;

}
