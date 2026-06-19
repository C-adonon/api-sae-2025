package future.SAE.application.exception;

public class FichierIntrouvableException extends RuntimeException
{
    public FichierIntrouvableException()
    {
        super("Fichier introuvable");
    }
}
