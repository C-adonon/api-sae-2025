package future.SAE.application.exception;

public class UtilisateurIntrouvableException extends RuntimeException
{
    public UtilisateurIntrouvableException()
    {
        super("Utilisateur introuvable");
    }
}
