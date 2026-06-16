package future.SAE.application.exception;

public class IdentifiantsInvalidesException extends RuntimeException {
    public IdentifiantsInvalidesException() {
        super("L'identifiant ou le mot de passe est incorrect.");
    }
}