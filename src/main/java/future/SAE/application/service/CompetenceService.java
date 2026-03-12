package future.SAE.application.service;

import future.SAE.domain.model.Competence;
import future.SAE.infrastructure.mapping.CompetenceMapper;
import future.SAE.infrastructure.persistence.CompetenceJPA;
import future.SAE.infrastructure.persistence.FormationJPA;
import future.SAE.infrastructure.repository.CompetenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceService {

    private final CompetenceRepository competenceRepository;
    private final CompetenceMapper competenceMapper;
    private final FormationRepository formationRepository;

    public CompetenceService(CompetenceRepository repository, CompetenceMapper mapper, FormationRepository formationRepository){
        this.competenceRepository = repository;
        this.competenceMapper = mapper;
        this.formationRepository = formationRepository;
    }

    public Competence createCompetence(Competence competence){
        CompetenceJPA jpaToSave = competenceMapper.toEntity(competence);
        CompetenceJPA jpaSaved = competenceRepository.save(jpaToSave);
        System.out.println("Compétence créé avec succès");
        return competenceMapper.toDomain(jpaSaved);
    }

    public List<Competence> getAllCompetences() {
        List<CompetenceJPA> competencesJPA = competenceRepository.findAll();
        return competenceMapper.toDomainList(competencesJPA);
    }

    public Competence getCompetenceById(Long id){
        CompetenceJPA competenceJPA = competenceRepository.findById(id)
                .orElseThrow(throw new RuntimeException("Cette compétence avec l'id " + id + " n'existe pas."));
        return competenceMapper.toDomain(competenceJPA);
    }

    public List<Competence> getCompetenceByIdFormation(Long idFormation){
        List<CompetenceJPA> competencesJPA = competenceRepository.findByFormation_Id(idFormation);
        return competenceMapper.toDomainList(competencesJPA);
    }

    public void updateCompetence(Long id, Competence newCompetence){
        CompetenceJPA competenceExistante = competenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impossible de modifier : la compétence avec l'ID " + id + " n'existe pas."));

        competenceExistante.setNumero(newCompetence.getNumero());
        competenceExistante.setLibelle(newCompetence.getLibelle());
        competenceExistante.setDescription(newCompetence.getDescription());

        if (newCompetence.getFormation() !null){
            Long idFormation = newCompetence.getFormation().getIdFormation();

            FormationJPA formationExistante = formationRepository.findById(idFormation)
                    .orElseThrow(() -> new RuntimeException("Impossible d'assigner la formation : l'ID " + idFormation + " n'existe pas."));

            competenceExistante.setFormation(formationExistante);
        }

        CompetenceJPA jpaSaved = competenceRepository.save(competenceExistante);

        return competenceMapper.toDomain(jpaSaved);
    }

    public void deleteCompetence(Long id) {
        if (!competenceRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : la compétence " + id + " n'existe pas.");
        }
        competenceRepository.deleteById(id);
    }

}
