package future.SAE.application.service;

import future.SAE.domain.model.Section;
import future.SAE.infrastructure.mapping.SectionMapper;
import future.SAE.infrastructure.persistence.SectionJPA;
import future.SAE.infrastructure.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;

    public SectionService(SectionRepository sectionRepository, SectionMapper sectionMapper) {
        this.sectionRepository = sectionRepository;
        this.sectionMapper = sectionMapper;
    }

    public Section createSection(Section section) {
        SectionJPA jpaToSave = sectionMapper.toEntity(section);
        SectionJPA jpaSaved = sectionRepository.save(jpaToSave);
        return sectionMapper.toDomain(jpaSaved);
    }

    public List<Section> getAllSections() {
        List<SectionJPA> sectionsJPA = sectionRepository.findAll();
        return sectionMapper.toDomainList(sectionsJPA);
    }

    public Section getSectionById(Long id) {
        SectionJPA sectionJPA = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La section avec l'id " + id + " n'existe pas."));
        return sectionMapper.toDomain(sectionJPA);
    }

    public List<Section> getSectionsByCoursId(Long idCours) {
        // On utilise la méthode du repository qui trie par ordre
        List<SectionJPA> sectionsJPA = sectionRepository.findByCoursIdCoursOrderByOrdreAsc(idCours);
        return sectionMapper.toDomainList(sectionsJPA);
    }

    public Section updateSection(Long id, Section newSection) {
        sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impossible de modifier : la section avec l'ID " + id + " n'existe pas."));

        SectionJPA jpaToUpdate = sectionMapper.toEntity(newSection);
        // On force l'ID pour l'update
        jpaToUpdate.setIdSection(id);

        SectionJPA jpaSaved = sectionRepository.save(jpaToUpdate);
        return sectionMapper.toDomain(jpaSaved);
    }

    public void deleteSection(Long id) {
        if (!sectionRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : la section " + id + " n'existe pas.");
        }
        sectionRepository.deleteById(id);
    }
}