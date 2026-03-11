package future.SAE.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("PROFESSEUR")
@Getter
@Setter
public class ProfesseurJPA extends UtilisateurJPA {
    @OneToMany(mappedBy = "professeur")
    private List<CoursJPA> coursDispenses = new ArrayList<>();

    private ProfesseurJPA() {
        super();
    }
}
