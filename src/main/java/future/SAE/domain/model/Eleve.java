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
