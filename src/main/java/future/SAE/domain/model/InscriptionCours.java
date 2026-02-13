package future.SAE.domain.model;

import java.time.LocalDateTime;

public class InscriptionCours {
    private Cours cours;
    private Etudiant etudiant;
    private LocalDateTime dateInscription = LocalDateTime.now();

    public InscriptionCours(Cours unCours, Etudiant unEtudiant) {
        this.cours = unCours;
        this.etudiant = unEtudiant;
    }

    public Cours getCours() {
        return this.cours;
    }

    public Etudiant getEtudiant() {
        return this.etudiant;
    }

    public LocalDateTime getDateInscription() {
        return dateInscription;
    }
}
