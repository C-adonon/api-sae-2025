package future.SAE.application.exception;

public class EmailDejaUtiliseException extends RuntimeException {

    public EmailDejaUtiliseException(String email) {
        super("L'adresse email '" + email + "' est déjà associée à un compte existant.");
    }
}