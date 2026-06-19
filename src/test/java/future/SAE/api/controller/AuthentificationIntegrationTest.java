package future.SAE.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import future.SAE.api.dto.requete.AuthentificationRequete;
import future.SAE.api.dto.requete.InscriptionEleveRequete;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.jayway.jsonpath.JsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthentificationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Inscription -> Connexion -> Accès Protégé")
    public void testFluxCompletAuthentification() throws Exception {

        InscriptionEleveRequete inscriptionReq = new InscriptionEleveRequete();
        inscriptionReq.setIdentifiant("E-TEST-001");
        inscriptionReq.setNom("Dupont");
        inscriptionReq.setPrenom("Jean");
        inscriptionReq.setEmail("jean.dupont@test.fr");
        inscriptionReq.setMotDePasse("SuperSecret123!");

        MvcResult resultInscription = mockMvc.perform(post("/api/auth/inscription/eleve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inscriptionReq)))
                .andExpect(status().isCreated()) // attend un 201 Created
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.role").value("ELEVE"))
                .andReturn();

        String jsonResponse = resultInscription.getResponse().getContentAsString();
        String utilisateurId = JsonPath.read(jsonResponse, "$.id");

        //  CONNEXION (RÉCUPÉRATION DU TOKEN)
        AuthentificationRequete loginReq = new AuthentificationRequete();
        loginReq.setIdentifiant("E-TEST-001");
        loginReq.setMotDePasse("SuperSecret123!");

        MvcResult resultConnexion = mockMvc.perform(post("/api/auth/connexion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginReq)))
                .andExpect(status().isOk()) // attend un 200 OK
                .andExpect(jsonPath("$.token").exists()) // On vérifie que le token est là
                .andReturn();

        String tokenResponse = resultConnexion.getResponse().getContentAsString();
        String token = JsonPath.read(tokenResponse, "$.token");


        //TENTATIVE D'ACCÈS SANS TOKEN (Doit échouer)
        mockMvc.perform(get("/api/utilisateurs/" + utilisateurId))
                .andExpect(status().isForbidden());


        // ACCÈS AVEC LE TOKEN (Doit réussir)
        mockMvc.perform(get("/api/utilisateurs/" + utilisateurId)
                        .header("Authorization", "Bearer " + token)) // On présente le token
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identifiant").value("E-TEST-001"));
    }

    @Test
    @DisplayName("Doit renvoyer 400 Bad Request si les données d'inscription sont invalides")
    public void testInscriptionInvalide() throws Exception {
        InscriptionEleveRequete requeteInvalide = new InscriptionEleveRequete();
        requeteInvalide.setIdentifiant("E-123");
        // On omet volontairement le nom, le prénom
        requeteInvalide.setEmail("mauvais-email"); //
        requeteInvalide.setMotDePasse("123");

        mockMvc.perform(post("/api/auth/inscription/eleve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requeteInvalide)))
                .andExpect(status().isBadRequest()) // 400 attendu (GlobalExceptionHandler)
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.motDePasse").exists())
                .andExpect(jsonPath("$.nom").exists());
    }
}