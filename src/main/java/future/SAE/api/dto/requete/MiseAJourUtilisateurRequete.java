package future.SAE.api.dto.requete;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Objet contenant les informations modifiables du profil")
public class MiseAJourUtilisateurRequete {

    @Schema(description = "Nouveau nom de famille", example = "Martin")
    private String nom;

    @Schema(description = "Nouveau prénom", example = "Jean")
    private String prenom;
}