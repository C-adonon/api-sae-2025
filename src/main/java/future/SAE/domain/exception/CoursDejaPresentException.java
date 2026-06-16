package future.SAE.domain.exception;

public class CoursDejaPresentException extends RuntimeException {
    public CoursDejaPresentException(String nomCours)
    {
        super("Le cours " + nomCours + " est déjà présent dans cette formation");
    }
}
