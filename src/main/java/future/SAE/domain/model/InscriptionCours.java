package future.SAE.domain.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscriptionCours {
    private Long idInscriptionCours;
    private Cours cours;
    private Eleve eleve;
    private LocalDateTime dateInscription = LocalDateTime.now();

    public InscriptionCours() {
    }

    public InscriptionCours(Cours unCours, Eleve unEleve) {
        this.cours = unCours;
        this.eleve = unEleve;
    }
}
