package future.SAE.domain.model;

import future.SAE.domain.valueObject.Role;

public class Eleve extends Utilisateur
{
    private Formation formation;

    // constructeur
    public Eleve(String unNom, String unPrenom, int identifiant, String unEmail, String unMdp)
    {
        super(unNom, unPrenom, identifiant, unEmail, unMdp, Role.ELEVE);
    }

    // ajouter constructeur avec l'id de la formation
    public Eleve(String unNom, String unPrenom, int identifiant, String unMdp, String unEmail, Formation uneFormation)
    {
        super(unNom, unPrenom, identifiant, unEmail, unMdp, Role.ELEVE);
        this.formation = uneFormation;
    }
