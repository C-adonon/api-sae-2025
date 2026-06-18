package future.SAE.api.controller;

import future.SAE.api.dto.reponse.UtilisateurReponse;
import future.SAE.api.dto.requete.AuthentificationRequete;
import future.SAE.api.dto.requete.InscriptionEleveRequete;
import future.SAE.api.dto.requete.InscriptionProfesseurRequete;
import future.SAE.api.mapping.UtilisateurDTOMapper;
import future.SAE.application.interfaces.IAuthentificationService;
import future.SAE.application.interfaces.IInscriptionService;
import future.SAE.domain.model.Utilisateur;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {

    private final IInscriptionService inscriptionService;
    private final IAuthentificationService authentificationService;
    private final UtilisateurDTOMapper dtoMapper;

    public AuthentificationController(
            IInscriptionService inscriptionService,
            IAuthentificationService authentificationService,
            UtilisateurDTOMapper dtoMapper) {
        this.inscriptionService = inscriptionService;
        this.authentificationService = authentificationService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping("/inscription/professeur")
    public ResponseEntity<UtilisateurReponse> inscrireProfesseur(@Valid @RequestBody InscriptionProfesseurRequete requete) {
        Utilisateur nouveauProf = inscriptionService.inscrireProfesseur(
                requete.getIdentifiant(), requete.getNom(), requete.getPrenom(),
                requete.getEmail(), requete.getMotDePasse()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(nouveauProf));
    }

    @PostMapping("/inscription/eleve")
    public ResponseEntity<UtilisateurReponse> inscrireEleve(@Valid @RequestBody InscriptionEleveRequete requete) {
        Utilisateur nouvelEleve = inscriptionService.inscrireEleve(
                requete.getIdentifiant(), requete.getNom(), requete.getPrenom(),
                requete.getEmail(), requete.getMotDePasse()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(nouvelEleve));
    }

    @PostMapping("/connexion")
    public ResponseEntity<UtilisateurReponse> connecter(@Valid @RequestBody AuthentificationRequete requete) {
        Utilisateur utilisateurConnecte = authentificationService.authentifier(
                requete.getIdentifiant(), requete.getMotDePasse()
        );
        return ResponseEntity.ok(dtoMapper.toResponse(utilisateurConnecte));
    }
}