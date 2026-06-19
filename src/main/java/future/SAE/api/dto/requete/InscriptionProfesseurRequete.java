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
@Schema(description = "Objet contenant les informations nécessaires pour inscrire un Professeur")
public class InscriptionProfesseurRequete {

    @NotBlank(message = "L'identifiant est obligatoire")
    @Schema(description = "Identifiant administratif du professeur", example = "P-2026-001")
    private String identifiant;

    @NotBlank(message = "Le nom est obligatoire")
    @Schema(description = "Nom de famille du professeur", example = "Martin")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Schema(description = "Prénom du professeur", example = "Sophie")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Le format de l'email est invalide")
    @Schema(description = "Adresse email de contact", example = "sophie.martin@ecole.fr")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "Le mot de passe doit faire au moins 8 caractères")
    @Schema(description = "Mot de passe sécurisé (minimum 8 caractères)", example = "ProfSecret123!")
    private String motDePasse;
}