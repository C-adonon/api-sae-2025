package future.SAE.application.services;

import future.SAE.application.exception.UtilisateurIntrouvableException;
import future.SAE.application.interfaces.ISecurityProvider;
import future.SAE.application.interfaces.IUtilisateurService;
import future.SAE.domain.interfaces.ICoursRepository;
import future.SAE.domain.interfaces.IMessageRepository;
import future.SAE.domain.interfaces.IUtilisateurRepository;
import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Message;
import future.SAE.domain.model.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UtilisateurService implements IUtilisateurService{

    private final IUtilisateurRepository utilisateurRepository;
    private final ICoursRepository coursRepository;
    private final IMessageRepository messageRepository;
    private final ISecurityProvider securityProvider;

    public UtilisateurService(IUtilisateurRepository utilisateurRepository, ICoursRepository coursRepository, IMessageRepository messageRepository, ISecurityProvider securityProvider)
    {
        this.utilisateurRepository = utilisateurRepository;
        this.coursRepository = coursRepository;
        this.messageRepository = messageRepository;
        this.securityProvider = securityProvider;
    }

    @Override
    public Utilisateur creerUtilisateur(String nom, String prenom, String identifiant, String email, String mdp)
    {
       String mdpHache = securityProvider.hacher(mdp);
       Utilisateur user = new Utilisateur(nom, prenom, identifiant, email, mdpHache);
       return utilisateurRepository.sauvegarder(user);
    }

    @Override
    public void modifierMdp(UUID id, String ancienMdp, String nouveauMdp)
    {
        Utilisateur user = utilisateurRepository.trouverParId(id).orElseThrow(UtilisateurIntrouvableException::new);
        String nouveauMpdHache = securityProvider.hacher(nouveauMdp);
        user.modifierMdp(ancienMdp, nouveauMpdHache);
        utilisateurRepository.sauvegarder(user);
    }

    @Override
    public List<Cours> accederCours(UUID id)
    {
        utilisateurRepository.trouverParId(id).orElseThrow(UtilisateurIntrouvableException::new);
        return coursRepository.trouverParUtilisateurId(id);
    }

    @Override
    public List<Message> accederMessage(UUID id)
    {
        utilisateurRepository.trouverParId(id).orElseThrow(UtilisateurIntrouvableException::new);
        return messageRepository.trouverParDestinataire(id);
    }

    @Override
    public void envoyerMessage(UUID expediteurId, UUID destinataireId, String contenu) {

    }

}
