package future.SAE.domain.interfaces;

import future.SAE.domain.model.Section;

import java.util.List;
import java.util.Optional;

public interface ISectionRepository
{
    Optional<Section> trouverParId(Long id);
    List<Section>trouverToutes();
    Section sauvegarder(Section section);
    void supprimer(Section section);
}
