package future.SAE.api.dto.reponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objet représentant les informations publiques d'un utilisateur (mot de passe masqué)")
public class UtilisateurReponse {

    @Schema(description = "Identifiant technique (UUID) généré par la base de données", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Identifiant métier de l'utilisateur", example = "E-2026-001")
    private String identifiant;

    @Schema(description = "Nom de famille", example = "Dupont")
    private String nom;

    @Schema(description = "Prénom", example = "Jean")
    private String prenom;

    @Schema(description = "Adresse email", example = "jean.dupont@ecole.fr")
    private String email;
}