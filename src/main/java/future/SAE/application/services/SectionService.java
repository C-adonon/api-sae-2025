package future.SAE.application.services;

import future.SAE.application.exception.CoursIntrouvableException;
import future.SAE.application.exception.SectionIntrouvableException;
import future.SAE.application.interfaces.ICoursService;
import future.SAE.application.interfaces.ISectionService;
import future.SAE.domain.interfaces.ICoursRepository;
import future.SAE.domain.interfaces.ISectionRepository;
import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Section;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService implements ISectionService
{
    private final ISectionRepository sectionRepository;
    private final ICoursRepository coursRepository;

    public SectionService(ISectionRepository sectionRepository, ICoursRepository coursRepository) {
        this.sectionRepository = sectionRepository;
        this.coursRepository = coursRepository;
    }

    @Override
    public Section creerSection(int ordre, String titre, String texte, Long coursId)
    {
        Cours c = coursRepository.trouverParId(coursId).orElseThrow(CoursIntrouvableException::new);
        Section s = new Section(ordre, titre, texte);
        s.setCours(c);
        return sectionRepository.sauvegarder(s);
    }

    @Override
    public Section modifierSection(Long id, String nouveauTitre, String nouveauTexte)
    {
        Section s = sectionRepository.trouverParId(id).orElseThrow(SectionIntrouvableException::new);
        s.setTitre(nouveauTitre);
        s.setTexte(nouveauTexte);
        return sectionRepository.sauvegarder(s);
    }

    @Override
    public Section accederSection(Long id)
    {
        return sectionRepository.trouverParId(id).orElseThrow(SectionIntrouvableException::new);
    }

    @Override
    public void supprimerSection(Long id)
    {
        Section s = sectionRepository.trouverParId(id).orElseThrow(SectionIntrouvableException::new);
        sectionRepository.supprimer(s);
    }

    @Override
    public List<Section> listerSection()
    {
        return sectionRepository.trouverToutes();
    }

    @Override
    public void ouvrirSection(Long id)
    {
        Section s = sectionRepository.trouverParId(id).orElseThrow(SectionIntrouvableException::new);
        s.ouvrirSection();
        sectionRepository.sauvegarder(s);
    }

    @Override
    public void fermerSection(Long id)
    {
        Section s =sectionRepository.trouverParId(id).orElseThrow(SectionIntrouvableException::new);
        s.fermerSection();
        sectionRepository.sauvegarder(s);
    }

}
