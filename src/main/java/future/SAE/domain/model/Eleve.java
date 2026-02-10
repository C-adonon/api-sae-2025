package future.SAE.domain.model;

public class Eleve extends Utilisateur
{
    private Formation formation;
    public Eleve(String unNom, String unPrenom, int id, String unEmail)
    {
        super(unNom, unPrenom, id, unEmail);
    }

    public Formation inscriptionFormation(Formation formation)
    {
        return this.formation = formation;
    }
}
