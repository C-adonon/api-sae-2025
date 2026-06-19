package future.SAE.domain.exception;

public class OrdreSectionExistantException extends RuntimeException {
    private final int ordre;

    public OrdreSectionExistantException(int unOrdre)
    {
        super("Une section a déja pour ordre " + unOrdre);
        this.ordre = unOrdre;
    }

    public int getOrdre() {
        return ordre;
    }
}
