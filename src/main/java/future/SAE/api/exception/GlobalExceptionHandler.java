package future.SAE.api.exception;

import future.SAE.application.exception.AccesRefuseException;
import future.SAE.application.exception.CompetenceIntrouvableException;
import future.SAE.application.exception.CoursIntrouvableException;
import future.SAE.application.exception.DejaInscritException;
import future.SAE.application.exception.EmailDejaUtiliseException;
import future.SAE.application.exception.EtatSectionIntrouvableException;
import future.SAE.application.exception.FichierIntrouvableException;
import future.SAE.application.exception.FormationIntrouvableException;
import future.SAE.application.exception.IdentifiantDejaUtiliseException;
import future.SAE.application.exception.IdentifiantsInvalidesException;
import future.SAE.application.exception.InscriptionCoursIntrouvableException;
import future.SAE.application.exception.MessageIntrouvableException;
import future.SAE.application.exception.SectionIntrouvableException;
import future.SAE.application.exception.SuiviCoursIntrouvableException;
import future.SAE.application.exception.UtilisateurIntrouvableException; // 👈 N'oublie pas cet import !
import future.SAE.domain.exception.FormationInvalideException;
import future.SAE.domain.exception.InscriptionInvalideException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IdentifiantDejaUtiliseException.class, EmailDejaUtiliseException.class})
    public ResponseEntity<Map<String, String>> gererDoublons(RuntimeException ex) {
        Map<String, String> erreur = new HashMap<>();
        erreur.put("erreur", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erreur);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> gererValidation(MethodArgumentNotValidException ex) {
        Map<String, String> erreurs = new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            erreurs.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erreurs);
    }

    @ExceptionHandler({InscriptionInvalideException.class, FormationInvalideException.class})
    public ResponseEntity<Map<String, String>> gererErreursDomaine(RuntimeException ex) {
        Map<String, String> erreur = new HashMap<>();
        erreur.put("erreur", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erreur);
    }

    @ExceptionHandler(IdentifiantsInvalidesException.class)
    public ResponseEntity<Map<String, String>> gererIdentifiantsInvalides(IdentifiantsInvalidesException ex) {
        Map<String, String> erreur = new HashMap<>();
        erreur.put("erreur", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erreur);
    }

    @ExceptionHandler({
            UtilisateurIntrouvableException.class,
            CoursIntrouvableException.class,
            FormationIntrouvableException.class,
            SectionIntrouvableException.class,
            FichierIntrouvableException.class,
            CompetenceIntrouvableException.class,
            EtatSectionIntrouvableException.class,
            SuiviCoursIntrouvableException.class,
            InscriptionCoursIntrouvableException.class,
            MessageIntrouvableException.class
    })
    public ResponseEntity<Map<String, String>> gererRessourceIntrouvable(RuntimeException ex) {
        Map<String, String> erreur = new HashMap<>();
        erreur.put("erreur", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erreur);
    }

    @ExceptionHandler(AccesRefuseException.class)
    public ResponseEntity<Map<String, String>> gererAccesRefuse(AccesRefuseException ex) {
        Map<String, String> erreur = new HashMap<>();
        erreur.put("erreur", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erreur);
    }

    @ExceptionHandler(DejaInscritException.class)
    public ResponseEntity<Map<String, String>> gererDejaInscrit(DejaInscritException ex) {
        Map<String, String> erreur = new HashMap<>();
        erreur.put("erreur", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erreur);
    }

}