package future.SAE.domain.model;
import java.time.LocalDateTime;
import java.util.UUID;

import future.SAE.domain.valueObject.Role;
import lombok.Getter;
import lombok.Getter;
@Getter
@Setter
public abstract class Utilisateur
{
    private UUID idUser;
    private int identifiant;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Role role;
    private LocalDateTime dateCreation = LocalDateTime.now();

    public Utilisateur()
    {

    }

    public Utilisateur(String unNom, String unPrenom, int unIdentifiant, String unEmail, String unMdp, Role unRole)
    {
        this.nom = unNom;
        this.prenom = unPrenom;
        this.identifiant = unIdentifiant;
        this.email = unEmail;
        this.motDePasse = unMdp;
        this.role = unRole;
    }

    public Utilisateur(String unNom, String unPrenom, int unIdentifiant, String unEmail, String unMdp)
    {
        this.nom = unNom;
        this.prenom = unPrenom;
        this.identifiant = unIdentifiant;
        this.email = unEmail;
        this.motDePasse = unMdp;
    }

    public String toString()
    {
        return "L'utilisateur " + this.nom + this.prenom + " a pour identifiant " + this.identifiant + ". Son mail est " + this.email;
    }
}




