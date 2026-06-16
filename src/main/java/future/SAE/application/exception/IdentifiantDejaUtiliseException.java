package future.SAE.application.exception;

public class IdentifiantDejaUtiliseException extends RuntimeException {

    public IdentifiantDejaUtiliseException(String identifiant) {
        super("L'identifiant '" + identifiant + "' est déjà attribué à un autre utilisateur.");
    }
}