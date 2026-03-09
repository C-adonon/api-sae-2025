package future.SAE.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.StoredProcedureParameter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private UUID idMessage;
    private String objet;
    private String texte;
    private List<Utilisateur> destinataires = new ArrayList<>();
    private Utilisateur expediteur;
    private Message messageParent;
    private List<Message> commentaires = new ArrayList<>();
    private LocalDateTime date;

    public Message() {

    }

    public Message(String unObjet, String unMessage, ArrayList<Utilisateur> listeDestinataires,
            Utilisateur unExpediteur) {
        this.objet = unObjet;
        this.texte = unMessage;
        this.destinataires = listeDestinataires;
        this.expediteur = unExpediteur;
        this.date = LocalDateTime.now();
    }

    public Message(String unObjet, String unMessage, Message unMessageParent, ArrayList<Utilisateur> listeDestinataires,
            Utilisateur unExpediteur) {
        this.objet = unObjet;
        this.texte = unMessage;
        this.messageParent = unMessageParent;
        this.destinataires = listeDestinataires;
        this.expediteur = unExpediteur;
        this.date = LocalDateTime.now();
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
        return "Message: " + this.objet + "de" + this.expediteur;
    }
}