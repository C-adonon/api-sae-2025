package future.SAE.api.dto.requete;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Objet contenant les informations nécessaires pour inscrire un Élève")
public class InscriptionEleveRequete {

    @NotBlank(message = "L'identifiant est obligatoire")
    @Schema(description = "Le numéro étudiant ou identifiant", example = "E-2026-001")
    private String identifiant;

    @NotBlank(message = "Le nom est obligatoire")
    @Schema(description = "Nom de famille de l'élève", example = "Dupont")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Schema(description = "Prénom de l'élève", example = "Jean")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Le format de l'email est invalide")
    @Schema(description = "Adresse email académique", example = "jean.dupont@ecole.fr")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "Le mot de passe doit faire au moins 8 caractères")
    @Schema(description = "Mot de passe sécurisé (minimum 8 caractères)", example = "SuperSecret123!")
    private String motDePasse;
}