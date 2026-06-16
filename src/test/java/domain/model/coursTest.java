package future.SAE.domain.model;

public class coursTest
{
    private Professeur p;
    private Formation f;
    private Cours c;

    @BeforeEach
    public void init()
    {
        p = new Professeur("EDOH-DAGNON", "Clarence", "14503586", "clarence.edohdagnon@edu.univ.fr","Cl@rence2006!");
        f = new Formation(1, "Informatique", p, Semestre.S1);
        c = new Cours("Reseau", p, f);
    }
    @Test
    public void creerCours()
    {
        Assert.isTrue(c.getNom.equals("Reseau"), "C'est un cours de réseau");
        Assert.isTrue(c.getResponsable.equals(p), "Le responsable du cours est Clarence");
        Assert.isTrue(c.getFormation.equals("Reseau"), "C'est un cours de la formation informatique");
    }
    @Test


}