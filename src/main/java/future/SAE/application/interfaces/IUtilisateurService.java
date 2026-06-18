package future.SAE.application.interfaces;

import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Message;
import future.SAE.domain.model.Utilisateur;

import java.util.List;
import java.util.UUID;

public interface IUtilisateurService
{
    Utilisateur creerUtilisateur(String nom, String prenom, String identifiant, String email, String mdp);
    void modifierMdp(UUID id, String ancienMdp, String nouveauMdp);
    List<Cours> accederCours(UUID id);
    List<Message> accederMessage(UUID id);
    void envoyerMessage(UUID expediteurId, UUID destinataireId, String contenu);

}
