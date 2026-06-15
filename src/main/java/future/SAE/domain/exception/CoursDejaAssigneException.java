package future.SAE.domain.exception;

public class CoursDejaAssigneException extends RuntimeException {
    public CoursDejaAssigneException(String nomCours) {
        super("Impossible d'ajouter le cours '" + nomCours + "' : il est déjà dispensé par un autre professeur.");
    }
}