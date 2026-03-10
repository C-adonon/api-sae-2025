package future.SAE.infrastructure.persistence;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formation")
    private FormationJPA formation;

    @OneToMany(mappedBy = "Eleve")
    private List<InscriptionCoursJPA> inscriptions = new ArrayList<>();

    public EleveJPA() {
        super();
    }

}
