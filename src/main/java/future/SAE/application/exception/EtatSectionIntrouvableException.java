package future.SAE.application.exception;

public class EtatSectionIntrouvableException extends RuntimeException {
    public EtatSectionIntrouvableException() {
        super("État de section introuvable");
    }
}
