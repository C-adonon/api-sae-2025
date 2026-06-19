package future.SAE.api.dto.reponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objet renvoyé lors d'une connexion réussie, contenant le token d'accès")
public class JwtDTOReponse {

    @Schema(description = "Le token JWT à inclure dans le header Authorization des futures requêtes",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFLTIwMjY...")
    private String token;
}