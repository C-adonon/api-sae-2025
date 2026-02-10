import java.util.Locale;

public class Utilisateur
{
    private String nom;
    private String prenom;
    private int identifiant;
    private String email;
    private String motDePasse;

    public Utilisateur(String unNom, String unPrenom, int id, String unEmail)
    {
        this.nom = unNom;
        this.prenom = unPrenom;
        this.identifiant = id;
        this.email = unEmail;
    }

    public boolean connexion(int id, String mdp)
    {
        if(this.identifiant == id && this.motDePasse == mdp)
        {
            return true;
        }
        else
        {
            String s = "identifiant ou mot de passe incorrect";
            return false;
        }
    }

    /*public boolean deconnexion()
    {

    }*/

    public String getNom()
    {
        return this.nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public int getIdentifiant()
    {
        return identifiant;
    }

    public String getEmail()
    {
        return email;
    }

    public String setNom(String nom)
    {
        return this.nom = nom;
    }

    public String setPrenom(String prenom)
    {
        return this.prenom = prenom;
    }

    public String setEmail(String email)
    {
        return this.email = email;
    }

    public String setMdp(String mdp)
    {
        return this.motDePasse = mdp;
    }


    public boolean modifierMdp()
    {
        if(setMdp(this.motDePasse) == this.motDePasse)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        return "L'utilisateur " + this.nom + this.prenom + " a pour identifiant " + this.identifiant + ". Son mail est " + this.email;
    }

}