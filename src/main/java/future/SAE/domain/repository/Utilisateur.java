package future.SAE.domain.repository;

import java.util.Optional;

import future.SAE.domain.model.Utilisateur;

public interface Utilisateur {
    Utilisateur save(Utilisateur utilisateur);

    Optional<Utilisateur> findById(int id);

    Utilisateur update(Utilisateur utilisateur);

    Utilisateur findByEmail(String email);

    void deleteById(int id);
}
