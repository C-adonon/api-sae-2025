package future.SAE.application.interfaces;

import future.SAE.domain.model.Section;

import java.util.List;

public interface ISectionService
{
    Section creerSection(int ordre, String titre, String texte, Long coursId);
    Section modifierSection(Long id, String nouveauTitre, String nouveauTexte);
    Section accederSection(Long id);
    void supprimerSection(Long id);
    List<Section> listerSection();
    void ouvrirSection(Long id);
    void fermerSection(Long id);
}
