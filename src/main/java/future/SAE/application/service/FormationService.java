package future.SAE.application.service;

import future.SAE.domain.model.Formation;
import future.SAE.infrastructure.mapping.FormationMapper;
import future.SAE.infrastructure.persistence.FormationJPA;
import future.SAE.infrastructure.repository.FormationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FormationService {

    private final FormationRepository formationRepository;
    private final FormationMapper formationMapper;

    public FormationService(FormationRepository formationRepository, FormationMapper formationMapper) {
        this.formationRepository = formationRepository;
        this.formationMapper = formationMapper;
    }

    public Formation createFormation(Formation formation) {
        FormationJPA jpaToSave = formationMapper.toEntity(formation);
        FormationJPA jpaSaved = formationRepository.save(jpaToSave);
        return formationMapper.toDomain(jpaSaved);
    }

    public List<Formation> getAllFormations() {
        List<FormationJPA> formationsJPA = formationRepository.findAll();
        return formationMapper.toDomainList(formationsJPA);
    }

    public Formation getFormationById(Long id) {
        FormationJPA formationJPA = formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cette formation avec l'id " + id + " n'existe pas."));
        return formationMapper.toDomain(formationJPA);
    }

    public Formation getFormationByResponsableId(UUID idProfesseur) {
        FormationJPA formationJPA = formationRepository.findByResponsableIdUser(idProfesseur);
        if (formationJPA == null) {
            return null;
        }
        return formationMapper.toDomain(formationJPA);
    }

    public Formation updateFormation(Long id, Formation newFormation) {
        formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impossible de modifier : la formation avec l'ID " + id + " n'existe pas."));

        FormationJPA jpaToUpdate = formationMapper.toEntity(newFormation);
        jpaToUpdate.setId(id);

        FormationJPA jpaSaved = formationRepository.save(jpaToUpdate);
        return formationMapper.toDomain(jpaSaved);
    }

    public void deleteFormation(Long id) {
        if (!formationRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : la formation " + id + " n'existe pas.");
        }
        formationRepository.deleteById(id);
    }
}