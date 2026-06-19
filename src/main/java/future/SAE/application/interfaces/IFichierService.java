package future.SAE.application.interfaces;

import future.SAE.domain.model.Fichier;
import future.SAE.domain.model.Section;
import future.SAE.domain.valueObject.Type;

import java.util.List;

public interface IFichierService {
    Fichier creerFichier(String titre, String description, String cheminFichier, Section section, Type type);

    Fichier modifierFichier(Long id, String titre, String description);

    Fichier accederFichier(Long id);

    List<Fichier> listerFichier();

    void supprimerFichier(Long id);
}