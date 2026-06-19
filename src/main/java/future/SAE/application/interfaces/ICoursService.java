package future.SAE.application.interfaces;

import future.SAE.domain.model.Cours;

import java.util.List;

public interface ICoursService
{
    Cours creerCours(String nom);
    Cours modifierCours(Long id, String nouveauNom);
    void supprimerCours(Long id);
    Cours accederCours(Long id);
    List<Cours> listerCours();
}

