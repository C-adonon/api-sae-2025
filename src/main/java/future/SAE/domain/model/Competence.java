package future.SAE.domain.model;

public class Competence {

    int numero;
    String libelle;
    String description;

    public Competence(int unNumero, String unLibelle){

        this.numero = unNumero;
        this.libelle = unLibelle;

    }

    public Competence(int unNumero, String unLibelle, String uneDescription){

        this.numero = unNumero;
        this.libelle = unLibelle;
        this.description = uneDescription;

    }

    public int getNumero() {
        return this.numero;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public void setNumero(int unNumero) {
        this.numero = unNumero;
    }

    public void setLibelle(String unLibelle) {
        this.libelle = unLibelle;
    }

    public void setDescription(String uneDescription) {
        this.description = uneDescription;
    }

    public String toString() {
        return "Compétence C" + this.numero + "\n"
                + "Libellé : " + this.libelle + "\n"
                + "Description : " + "\n";
    }
}
