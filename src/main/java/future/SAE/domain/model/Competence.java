package future.SAE.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Competence {

    private Long idCompetence;
    private int numero;
    private String libelle;
    private String description;
    private Formation formation;

    public Competence(){
    }

    public Competence(int unNumero, String unLibelle, Formation uneFormation){
        this.numero = unNumero;
        this.libelle = unLibelle;
        this.formation = uneFormation;
    }

    public Competence(int unNumero, String unLibelle, Formation uneFormation, String uneDescription){
        this.numero = unNumero;
        this.libelle = unLibelle;
        this.description = uneDescription;
        this.formation = uneFormation;
    }


    public String toString() {
        return "Compétence C" + this.getNumero() + "\n"
                + "Libellé : " + this.getLibelle() + "\n"
                + "Description : " + this.getDescription() + "\n";
    }
}
