package future.SAE.api.dto.requete;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Objet contenant les identifiants pour se connecter à l'API")
public class AuthentificationRequete {

    @NotBlank(message = "L'identifiant est obligatoire")
    @Schema(description = "Identifiant unique de l'utilisateur", example = "E-2026-001")
    private String identifiant;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Schema(description = "Mot de passe en clair", example = "SuperSecret123!")
    private String motDePasse;
}