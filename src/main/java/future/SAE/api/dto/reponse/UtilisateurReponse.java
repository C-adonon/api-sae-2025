package future.SAE.api.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurReponse {
    private UUID id;
    private String identifiant;
    private String nom;
    private String prenom;
    private String email;
}