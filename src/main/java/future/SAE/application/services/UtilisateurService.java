package future.SAE.application.services;

import future.SAE.application.exception.UtilisateurIntrouvableException;
import future.SAE.application.interfaces.IUtilisateurService;
import future.SAE.domain.interfaces.IUtilisateurRepository;
import future.SAE.domain.model.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilisateurService implements IUtilisateurService {

    private final IUtilisateurRepository utilisateurRepository;

    public UtilisateurService(IUtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur consulterProfil(UUID id) {
        return utilisateurRepository.trouverParId(id)
                .orElseThrow(() -> new UtilisateurIntrouvableException("Utilisateur introuvable avec l'ID : " + id));
    }

    @Override
    public Utilisateur modifierProfil(UUID id, String nouveauNom, String nouveauPrenom) {
        Utilisateur utilisateurExistant = consulterProfil(id);

        utilisateurExistant.setNom(nouveauNom);
        utilisateurExistant.setPrenom(nouveauPrenom);

        return utilisateurRepository.sauvegarder(utilisateurExistant);
    }
}