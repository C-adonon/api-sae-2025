package future.SAE.application.exception;

/**
 * Levée lorsqu'un utilisateur authentifié tente une action qui ne lui est pas autorisée
 * (ex : un élève qui accède à un cours non public auquel il n'est pas inscrit).
 */
public class AccesRefuseException extends RuntimeException {
    public AccesRefuseException(String message) {
        super(message);
    }
}
