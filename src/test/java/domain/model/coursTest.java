public class coursTest
{
    @Test
    public void creerCours()
    {
        Professeur p = new Professeur("EDOH-DAGNON", "Clarence", "14503586", "clarence.edohdagnon@edu.univ.fr","Cl@rence2006!");
        Formation f = new Formation(1, "Informatique", p, Semestre.S1);
        Cours c = new Cours("Reseau", p, f);
        Assert.isTrue(c.getNom.equals("Reseau"), "C'est un cours de réseau");
        Assert.isTrue(c.getResponsable.equals(p), "Le responsable du cours est Clarence");
        Assert.isTrue(c.getFormation.equals("Reseau"), "C'est un cours de la formation informatique");
    }

}