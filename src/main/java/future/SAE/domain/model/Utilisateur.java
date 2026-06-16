package future.SAE.domain.model;

import java.sql.Timestamp;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Utilisateur {
    private UUID id;
    private String identifiant;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Timestamp dateCreation = new Timestamp(System.currentTimeMillis());

    // constructeur vide
    public Utilisateur() {
    }

    // constructeur d'un utilisateur sans role
    public Utilisateur(String unNom, String unPrenom, String unIdentifiant, String unEmail, String unMdp) {
        this.nom = unNom;
        this.prenom = unPrenom;
        this.identifiant = unIdentifiant;
        this.email = unEmail;
        this.motDePasse = unMdp;
    }

    public boolean modifierMdp(String ancienMdp, String newMdp) {
        if (ancienMdp.equals(motDePasse)) {
            this.motDePasse = newMdp;
            return true;
        }
        throw new IllegalArgumentException("L'ancien mot de passe est incorrect.");
    }

    // affichage de l'utilisateur lors des test
    public String toString() {
        return "L'utilisateur " + this.nom + this.prenom + " a pour identifiant " + this.identifiant
                + " et son mail est " + this.email;
    }
}