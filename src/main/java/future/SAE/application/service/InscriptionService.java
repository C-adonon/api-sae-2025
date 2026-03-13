package future.SAE.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Eleve;
import future.SAE.domain.model.InscriptionCours;
import future.SAE.infrastructure.mapping.InscriptionCoursMapper;
import future.SAE.infrastructure.persistence.InscriptionCoursJPA;
import future.SAE.infrastructure.repository.InscriptionCoursRepository;

@Service
public class InscriptionService {

    private final InscriptionCoursRepository inscriptionCoursRepository;
    private final InscriptionCoursMapper inscriptionCoursMapper;

    public InscriptionService(InscriptionCoursRepository inscriptionCoursRepository, InscriptionCoursMapper inscriptionCoursMapper) {
        this.inscriptionCoursRepository = inscriptionCoursRepository;
        this.inscriptionCoursMapper = inscriptionCoursMapper;
    }



    public boolean inscrireEleveAuCours(Cours cours, Eleve eleve) {
        try {
            InscriptionCoursJPA inscription = inscriptionCoursMapper.toEntity(new InscriptionCours(cours, eleve));
            inscriptionCoursRepository.save(inscription);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean desinscrireEleveDuCours(Long idCours, UUID idEleve) {
        try {
            var inscription =
            inscriptionCoursRepository.findByCoursIdCoursAndEleveIdUser(idCours,
            idEleve);
            if (inscription != null) {
            inscriptionCoursRepository.delete(inscription);
            return true;
        }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean estEleveInscritAuCours(Long idCours, UUID idEleve) {
        return inscriptionCoursRepository.existsByCoursIdCoursAndEleveIdUser(idCours, idEleve);
    }

    public int nombreElevesInscritsAuCours(Long idCours) {
        return inscriptionCoursRepository.countByCoursIdCours(idCours);
    }

    public int nombreCoursSuivisParEleve(UUID idEleve) {
        return inscriptionCoursRepository.countByEleveIdUser(idEleve);
    }

}
