package future.SAE.api.exception;

import future.SAE.application.exception.EmailDejaUtiliseException;
import future.SAE.application.exception.IdentifiantDejaUtiliseException;
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

        erreur.put("erreur_metier", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erreur);
    }
}