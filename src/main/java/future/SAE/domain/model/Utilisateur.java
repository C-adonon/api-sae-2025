package future.SAE.domain.model;
import java.time.LocalDateTime;
import java.util.UUID;

import future.SAE.domain.valueObject.Role;
import lombok.Getter;
import lombok.Getter;

@Getter
@Setter
public class Utilisateur
{
    private UUID idUser;
    private int identifiant;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Role role;
    private LocalDateTime dateCreation = LocalDateTime.now();

    //constructeur vide
    public Utilisateur()
    {

    }
    //constructeur d'un utilisateur avec un role: professeur ou eleve
    public Utilisateur(String unNom, String unPrenom, int unIdentifiant, String unEmail, String unMdp, Role unRole)
    {
        this.nom = unNom;
        this.prenom = unPrenom;
        this.identifiant = unIdentifiant;
        this.email = unEmail;
        this.motDePasse = unMdp;
        this.role = unRole;
    }

    //constructeur d'un utilisateur sans role
    public Utilisateur(String unNom, String unPrenom, int unIdentifiant, String unEmail, String unMdp)
    {
        this.nom = unNom;
        this.prenom = unPrenom;
        this.identifiant = unIdentifiant;
        this.email = unEmail;
        this.motDePasse = unMdp;
    }
    // affichage de l'utilisateur lors des test
    public String toString()
    {
        return "L'utilisateur " + this.nom + this.prenom + " a pour identifiant " + this.identifiant + ". Son mail est " + this.email;
    }
}




