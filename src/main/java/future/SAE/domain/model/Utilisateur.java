package future.SAE.domain.model;
import java.time.LocalDateTime;
//import java.util.Locale;
import java.util.UUID;
import future.SAE.domain.valueObject.Role;

// ajouter un uuid pour l'identifiant de l'utilisateur ?
//ajouter un champs date de création du compte ?
// ajouter un role pour différencier les élèves des professeurs ?
// ajouter un 2eme constructeur
public class Utilisateur
{
    private UUID id;
    private int identifiant;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Role role;
    private LocalDateTime dateCreation = LocalDateTime.now();

    public Utilisateur(String unNom, String unPrenom, int identifiant, String unEmail, String unMdp, Role unRole)
    {
        this.identifiant = identifiant;
        this.nom = unNom;
        this.prenom = unPrenom;
        this.email = unEmail;
        this.motDePasse = unMdp;
        this.role = unRole;
    }

    public Utilisateur(String unNom, String unPrenom, int identifiant, String unEmail, String unMdp)
    {
        this.identifiant = identifiant;
        this.nom = unNom;
        this.prenom = unPrenom;
        this.email = unEmail;
        this.motDePasse = unMdp;
    }

    public boolean connexion(int id, String mdp)
    {
        if (this.identifiant == id && this.motDePasse == mdp)
        {
            return true;
        }
        else
        {
            // a quoi correspond le "s", ou va t-il être utilisé ?
            String s = "identifiant ou mot de passe incorrect";
            return false;
        }
    }

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

    // /!\ Cette methode ne fonctionne pas, elle est à revoir
    // public boolean modifierMdp() {
    // if (setMdp(this.motDePasse) == this.motDePasse) {
    // return true;
    // } else {
    // return false;
    // }
    // }

    public String toString()
    {
        return "L'utilisateur " + this.nom + this.prenom + " a pour identifiant " + this.identifiant + ". Son mail est "
                + this.email;
    }


