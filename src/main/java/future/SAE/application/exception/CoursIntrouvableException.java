package future.SAE.application.exception;

public class CoursIntrouvableException extends RuntimeException
{
    public CoursIntrouvableException()
    {
        super("Cours introuvable");
    }
}
