package future.SAE.domain.exception;

public class MotDePasseIncorrectException extends RuntimeException {
    public MotDePasseIncorrectException() {
        super("L'ancien mot de passe fourni est incorrect.");
    }
}