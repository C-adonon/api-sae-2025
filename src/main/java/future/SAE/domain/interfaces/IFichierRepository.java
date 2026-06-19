package future.SAE.domain.interfaces;

import future.SAE.domain.model.Fichier;

import java.util.List;
import java.util.Optional;

public interface IFichierRepository
{
    Fichier sauvegarder(Fichier fichier);
    Optional<Fichier> trouverParId(Long id);
    List<Fichier> trouverTous();
    void supprimer(Fichier fichier);
}
