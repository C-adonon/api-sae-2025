package future.SAE.domain.interfaces;

import future.SAE.domain.model.Cours;

import java.util.List;
import java.util.UUID;

public interface ICoursRepository
{
    List<Cours> trouverParUtilisateurId(UUID utilisateurId);
}
