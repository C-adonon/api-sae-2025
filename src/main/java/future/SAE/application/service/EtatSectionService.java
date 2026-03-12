package future.SAE.application.service;

import future.SAE.domain.valueObject.EtatSection;
import future.SAE.infrastructure.mapping.EtatSectionMapper;
import future.SAE.infrastructure.persistence.EtatSectionJPA;
import future.SAE.infrastructure.repository.EtatSectionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EtatSectionService {

    private final EtatSectionRepository etatSectionRepository;
    private final EtatSectionMapper etatSectionMapper;

    public EtatSectionService(EtatSectionRepository repository, EtatSectionMapper mapper){

        this.etatSectionRepository = repository;
        this.etatSectionMapper = mapper;
    }

    public EtatSection createEtatSection(EtatSection newEtatSection){
        EtatSectionJPA jpa = etatSectionMapper.toEntity(newEtatSection);
        EtatSectionJPA jpaSaved = etatSectionRepository.save(jpa);
        return etatSectionMapper.toDomain(jpaSaved);
    }

    public EtatSection getEtatSectionById(Long id){
        EtatSectionJPA etatSectionJPA = etatSectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impossible de trouver un EtatSection avec l'ID" + id));
        return etatSectionMapper.toDomain(etatSectionJPA);
    }

    public List<EtatSection> getAllEtatSection(){
        List<EtatSectionJPA> etatsSectionsJPA = etatSectionRepository.findAll();
        return etatSectionMapper.toDomainList(etatsSectionsJPA);
    }

    public List<EtatSection> getEtatSectionByEleve(UUID idEleve){
        List<EtatSectionJPA> jpaList = etatSectionRepository.findByEleve_IdUser(idEleve);
        return etatSectionMapper.toDomainList(jpaList);
    }

    public List<EtatSection> getEtatSectionByEleveAndEstTermineeTrue(UUID idEleve){
        List<EtatSectionJPA> jpaList = etatSectionRepository.findByEleve_IdUserAndEstTermineeTrue(idEleve);
        return etatSectionMapper.toDomainList(jpaList);
    }

    public EtatSection validerSectionTerminee(Long id){
        EtatSectionJPA jpa = etatSectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impossible de trouver un EtatSection avec l'ID" + id));

        EtatSection domain = etatSectionMapper.toDomain(jpa);

        domain.marquerCommeTerminee();
        domain.setDateCompletion(LocalDateTime.now());

        EtatSectionJPA jpaModifie = etatSectionMapper.toEntity(domain);
        EtatSectionJPA jpaSaved = etatSectionRepository.save(jpaModifie);

        return etatSectionMapper.toDomain(jpaSaved);
    }

    public void deleteEtatSection(Long id){
        if(!etatSectionRepository.existsById(id)){
            throw new RuntimeException("Impossible de supprimer : Impossible de trouver un EtatSection avec l'ID" + id);
        }
        etatSectionRepository.deleteById(id);
    }

}
