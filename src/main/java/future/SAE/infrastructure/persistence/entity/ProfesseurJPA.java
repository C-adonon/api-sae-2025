package future.SAE.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("PROFESSEUR")
@Getter
@Setter
@NoArgsConstructor
public class ProfesseurJPA extends UtilisateurJPA {

}