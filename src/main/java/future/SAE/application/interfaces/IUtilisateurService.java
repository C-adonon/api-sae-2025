package future.SAE.application.interfaces;

import future.SAE.domain.model.Utilisateur;
import java.util.UUID;

public interface IUtilisateurService {

    Utilisateur consulterProfil(UUID id);

    Utilisateur modifierProfil(UUID id, String nouveauNom, String nouveauPrenom);
}