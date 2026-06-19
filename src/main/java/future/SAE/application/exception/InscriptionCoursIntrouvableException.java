package future.SAE.application.exception;

public class InscriptionCoursIntrouvableException extends RuntimeException {
    public InscriptionCoursIntrouvableException() {
        super("Inscription au cours introuvable");
    }
}
