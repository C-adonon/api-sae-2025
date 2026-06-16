package future.SAE.domain.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscriptionCours {
    private Long idInscriptionCours;
    private Cours cours;
    private Eleve eleve;
    private Timestamp dateInscription = new Timestamp(System.currentTimeMillis());

    public InscriptionCours() {
    }

    public InscriptionCours(Cours unCours, Eleve unEleve) {
        this.cours = unCours;
        this.eleve = unEleve;
    }
}
