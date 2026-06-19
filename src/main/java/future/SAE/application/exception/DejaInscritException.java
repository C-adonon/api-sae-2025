package future.SAE.application.exception;

/**
 * Levée lorsqu'un élève tente de s'inscrire à un cours auquel il est déjà inscrit.
 */
public class DejaInscritException extends RuntimeException {
    public DejaInscritException() {
        super("L'élève est déjà inscrit à ce cours.");
    }
}
