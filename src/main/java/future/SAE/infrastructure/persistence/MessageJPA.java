package future.SAE.infrastructure.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "message")
@Getter
@Setter
public class MessageJPA {
    @Id
    @Column(name = "id_message", columnDefinition = "uuid", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMessage;

    @Column(nullable = false)
    private String objet;

    @Column(columnDefinition = "TEXT")
    private String texte;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UtilisateurJPA expediteur;

    @ManyToMany
    @JoinTable(name = "message_destinataire", joinColumns = @JoinColumn(name = "id_message"), inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<UtilisateurJPA> destinataires = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_message_parent")
    private MessageJPA messageParent;

    @OneToMany(mappedBy = "messageParent", orphanRemoval = true)
    private List<MessageJPA> commentaires = new ArrayList<>();

    private LocalDateTime date;

    public MessageJPA() {
    }
}
