package future.SAE.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Message {

    private String objet;
    private String message;
    private LocalDateTime date;
    private List<Utilisateur> destinataires;
    private Utilisateur expediteur;
    private List<Message> commentaires;

    public Message(String objet, String message, List<Utilisateur> destinataires, Utilisateur expediteur) {
        this.objet = objet;
        this.message = message;
        this.destinataires = destinataires;
        this.expediteur = expediteur;
        this.date = LocalDateTime.now();
        this.commentaires = new ArrayList<>();
    }

    // getters
    public String getObjet() {
        return objet;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Utilisateur> getDestinataires() {
        return destinataires;
    }

    public Utilisateur getExpediteur() {
        return expediteur;
    }

    public List<Message> getCommentaires() {
        return commentaires;
    }

    // setters
    public void setObjet(String texte) {
        this.objet = texte;
    }

    public void setMessage(String texte) {
        this.message = texte;
    }

    public void setDestinataires(List<Utilisateur> destinataires) {
        this.destinataires = destinataires;
    }

    public boolean commenter() {
        return true;
    }

    public boolean envoyer() {
        return true;
    }

    public boolean supprimer() {
        return true;
    }

    @Override
    public String toString() {
        return "Message: " + this.objet + " (de " + this.expediteur + ")";
    }
}
