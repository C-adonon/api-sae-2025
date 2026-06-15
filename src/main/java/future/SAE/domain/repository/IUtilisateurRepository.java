package future.SAE.domain.repository;

import future.SAE.domain.model.Utilisateur;
import java.util.Optional;
import java.util.UUID;

public interface IUtilisateurRepository {

    public abstract Optional<Utilisateur> trouverParIdentifiant(String identifiant);
    public abstract Optional<Utilisateur> trouverParId(UUID id);
    public abstract Optional<Utilisateur> trouverParEmail(String email);
    public abstract Utilisateur sauvegarder(Utilisateur utilisateur);
}