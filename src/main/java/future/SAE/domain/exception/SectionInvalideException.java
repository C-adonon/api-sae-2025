package future.SAE.domain.exception;

public class SectionInvalideException extends RuntimeException {
    public SectionInvalideException() {
        super("Impossible d'ajouter une section vide à un cours");
    }
}
