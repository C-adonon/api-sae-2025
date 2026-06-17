package future.SAE.domain.interfaces;

import future.SAE.domain.model.Utilisateur;
import java.util.Optional;
import java.util.UUID;

public interface IUtilisateurRepository {

    public Optional<Utilisateur> trouverParIdentifiant(String identifiant);
    public Optional<Utilisateur> trouverParId(UUID id);
    public Optional<Utilisateur> trouverParEmail(String email);
    public Utilisateur sauvegarder(Utilisateur utilisateur);
}