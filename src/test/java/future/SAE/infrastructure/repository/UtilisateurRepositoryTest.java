package future.SAE.infrastructure.repository;

import future.SAE.domain.model.Professeur;
import future.SAE.domain.model.Utilisateur;
import future.SAE.infrastructure.mapping.UtilisateurMapperImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Import({UtilisateurRepository.class, UtilisateurMapperImpl.class})
public class UtilisateurRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @DisplayName("Doit sauvegarder et retrouver un utilisateur en base de données")
    void sauvegarderEtTrouverUtilisateur() {

        Professeur prof = new Professeur("Doe", "John", "PROF-001", "john.doe@ecole.fr", "mdp123");

        Utilisateur profSauvegarde = utilisateurRepository.sauvegarder(prof);

        Optional<Utilisateur> profTrouve = utilisateurRepository.trouverParIdentifiant("PROF-001");

        assertTrue(profTrouve.isPresent(), "L'utilisateur devrait être trouvé en base");
        assertEquals("Doe", profTrouve.get().getNom());
        assertEquals("PROF-001", profTrouve.get().getIdentifiant());


        assertNotNull(profSauvegarde.getId(), "L'ID ne devrait pas être nul après la sauvegarde");
        assertEquals(profSauvegarde.getId(), profTrouve.get().getId());
    }

    @Test
    @DisplayName("Doit retourner un Optional vide si l'utilisateur est introuvable")
    void trouverParIdentifiant_Inconnu() {
        Optional<Utilisateur> resultat = utilisateurRepository.trouverParIdentifiant("FANTOME-404");


        assertTrue(resultat.isEmpty(), "Le résultat devrait être vide pour un utilisateur inexistant");
    }

    @Test
    @DisplayName("Doit mettre à jour un utilisateur existant sans créer de doublon")
    void majUtilisateur() {

        Professeur prof = new Professeur("Doe", "John", "PROF-002", "john.doe@ecole.fr", "mdp123");
        Utilisateur profSauvegarde = utilisateurRepository.sauvegarder(prof);


        UUID idOriginal = profSauvegarde.getId();


        profSauvegarde.setNom("Smith");


        Utilisateur profMisAJour = utilisateurRepository.sauvegarder(profSauvegarde);


        assertEquals(idOriginal, profMisAJour.getId(), "L'ID ne doit pas changer lors d'une mise à jour");
        assertEquals("Smith", profMisAJour.getNom(), "Le nom doit avoir été mis à jour");


        Optional<Utilisateur> verifEnBase = utilisateurRepository.trouverParIdentifiant("PROF-002");
        assertTrue(verifEnBase.isPresent());
        assertEquals("Smith", verifEnBase.get().getNom());
    }
}