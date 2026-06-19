package future.SAE.application.services;
import future.SAE.application.services.UtilisateurService;

import future.SAE.application.interfaces.ISecurityProvider;
import future.SAE.domain.interfaces.ICoursRepository;
import future.SAE.domain.interfaces.IMessageRepository;
import future.SAE.domain.interfaces.IUtilisateurRepository;
import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Message;
import future.SAE.domain.model.Utilisateur;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTest
{
    @Mock private IUtilisateurRepository utilisateurRepository;
    @Mock private ICoursRepository coursRepository;
    @Mock private IMessageRepository messageRepository;
    @Mock private ISecurityProvider securityProvider;

    @InjectMocks
    private UtilisateurService utilisateurService;

    private UUID id;
    private UUID expId;
    private UUID destId;

    private void appelModifierMdpIntrouvable()
    {
        utilisateurService.modifierMdp(id, "Marseille", "Toulouse");
    }

    private void appelAccederCoursIntrouvable()
    {
        utilisateurService.accederCours(id);
    }

    private void appelAccederMessageIntrouvable()
    {
        utilisateurService.accederMessage(id);
    }

    private void appelEnvoyerMessageExpediteurIntrouvale()
    {
        utilisateurService.envoyerMessage(expId, UUID.randomUUID(), "Hello");
    }

    private void appelEnvoyerMessageDestinataireIntrouvale()
    {
        utilisateurService.envoyerMessage(expId, destId, "Hello");
    }

    @Test
    void creerUtilisateur()
    {
        when(securityProvider.hacher("Chloe-2000!")).thenReturn("Chl0e2000!");
        when(utilisateurRepository.sauvegarder(any())).thenReturn(new Utilisateur("Adonon", "Chloe", "14702581", "chloe.adonon@edu.univ-paris13.fr", "Chl0e2000!"));

        Utilisateur result = utilisateurService.creerUtilisateur("Adonon", "Chloe", "14702581", "chloe.adonon@edu.univ-paris13.fr", "Chloe-2000!");

        assertThat(result.getMotDePasse()).isEqualTo("Chl0e2000!");
        verify(securityProvider).hacher("Chloe-2000!");
        verify(utilisateurRepository).sauvegarder(any());
    }

    @Test
    void modifierMdpSiMdpIncorrect()
    {
        id = UUID.randomUUID();
        Utilisateur user = new Utilisateur("Adonon", "Chloe", "14702581", "chloe.adonon@edu.univ-paris13.fr", "Chl0e2000!");
        when(utilisateurRepository.trouverParId(id)).thenReturn(Optional.of(user));
        when(securityProvider.hacher("Chloe-2000!")).thenReturn("Chloe-2000!");
        when(securityProvider.verifier("Chl0e2000!", "Chl0e2000!")).thenReturn(true);

        utilisateurService.modifierMdp(id, "Chloe-2000!", "Chl0e2000!");

        verify(utilisateurRepository).sauvegarder(user);
    }

    @Test
    void accederCoursListe()
    {
        id = UUID.randomUUID();
        Utilisateur user = new Utilisateur("Bendjebbour", "Yasmine", "12301458", "yasmine.bendjebbour@edu.univ-paris13.fr", "Y@smine2005.");
        List<Cours> c = List.of(new Cours("Java", null, null), new Cours("Python", null, null));
        when(utilisateurRepository.trouverParId(id)).thenReturn(Optional.of(user));
        when(coursRepository.trouverParUtilisateurId(id)).thenReturn(c);

        List<Cours> res = utilisateurService.accederCours(id);

        assertThat(res).hasSize(2);
        verify(coursRepository).trouverParUtilisateurId(id);
    }

    @Test
    void accederMessage()
    {
        id = UUID.randomUUID();
        Utilisateur user = new Utilisateur("Edoh-Dagnon", "Clarence", "13601859", "clarence.edohdagnon@edu.univ-paris13.fr", "Cl@rence2006.");
        List<Message> m = List.of(new Message(), new Message());
        when(utilisateurRepository.trouverParId(id)).thenReturn(Optional.of(user));
        when(messageRepository.trouverParDestinataire(id)).thenReturn(m);

        List<Message> res = utilisateurService.accederMessage(id);

        assertThat(res).hasSize(2);

        verify(messageRepository).trouverParDestinataire(id);
    }

    void envoyerMessage()
    {
        expId = UUID.randomUUID();
        destId = UUID.randomUUID();
        Utilisateur exp = new Utilisateur("Edoh-Dagnon", "Clarence", "13601859", "clarence.edohdagnon@edu.univ-paris13.fr", "Cl@rence2006.");
        when(utilisateurRepository.trouverParId(expId)).thenReturn(Optional.of(exp));
        when(utilisateurRepository.trouverParId(destId)).thenReturn(Optional.of(new Utilisateur()));

        utilisateurService.envoyerMessage(expId, destId, "Hello");

        verify(messageRepository).sauvegarder(any(Message.class));
    }
}
