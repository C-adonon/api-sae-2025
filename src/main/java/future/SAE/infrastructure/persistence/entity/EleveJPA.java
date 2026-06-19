package future.SAE.infrastructure.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("ELEVE")
@Getter
@Setter
public class EleveJPA extends UtilisateurJPA {
    // 1. Un élève appartient à UNE formation
    // TODO: À décommenter quand la classe FormationEntity existera
    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formation_id")
    private FormationEntity formation;
    */

    // 2. Un élève a PLUSIEURS inscriptions à des cours
    // Le "mappedBy = 'eleve'" indique que c'est InscriptionCoursEntity qui gère la clé étrangère
    // TODO: À décommenter quand la classe InscriptionCoursEntity existera
    /*
    @OneToMany(mappedBy = "eleve", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InscriptionCoursEntity> inscriptions = new ArrayList<>();
    */

}
