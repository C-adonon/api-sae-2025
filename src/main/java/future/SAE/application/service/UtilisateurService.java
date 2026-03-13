package future.SAE.application.service;

import future.SAE.domain.model.Utilisateur;
import future.SAE.infrastructure.mapping.UtilisateurMapper;
import future.SAE.infrastructure.persistence.UtilisateurJPA;
import future.SAE.infrastructure.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    @Transactional
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()) {
            throw new RuntimeException("Un utilisateur avec cet email existe déjà.");
        }
        UtilisateurJPA jpaToSave = utilisateurMapper.mapUtilisateurToEntity(utilisateur);
        UtilisateurJPA jpaSaved = utilisateurRepository.save(jpaToSave);
        return utilisateurMapper.mapUtilisateurToDomain(jpaSaved);
    }

    @Transactional(readOnly = true)
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll().stream()
                .map(utilisateurMapper::mapUtilisateurToDomain)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Utilisateur getUtilisateurById(UUID id) {
        UtilisateurJPA utilisateurJPA = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("L'utilisateur avec l'id " + id + " n'existe pas."));
        return utilisateurMapper.mapUtilisateurToDomain(utilisateurJPA);
    }

    @Transactional
    public Utilisateur updateUtilisateur(UUID id, Utilisateur newUtilisateur) {
        utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impossible de modifier : l'utilisateur avec l'ID " + id + " n'existe pas."));

        UtilisateurJPA jpaToUpdate = utilisateurMapper.mapUtilisateurToEntity(newUtilisateur);
        jpaToUpdate.setIdUser(id);

        UtilisateurJPA jpaSaved = utilisateurRepository.save(jpaToUpdate);
        return utilisateurMapper.mapUtilisateurToDomain(jpaSaved);
    }

    @Transactional
    public void deleteUtilisateur(UUID id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : l'utilisateur " + id + " n'existe pas.");
        }
        utilisateurRepository.deleteById(id);
    }
}