package future.SAE.domain.model;

public class coursTest
{
    private String nom;
    private String description;
    private Professeur p;
    private Formation f;
    private Cours c;

    @BeforeEach
    public void init()
    {
        nom = "Reseau";
        description = "Initiation aux reseaux informatique";
        p = new Professeur("EDOH-DAGNON", "Clarence", "14503586", "clarence.edohdagnon@edu.univ.fr","Cl@rence2006!");
        f = new Formation(1, "Informatique", p, Semestre.S1);
        c = new Cours(nom, description, p, f);
    }
    @Test
    public void creerCours()
    {
        assertEquals(c.getNom(), nom);
        assertEquals(c.getDescription(), description);
        assertEquals(c.getProfesseur(), p);
        assertEquals(c.getFormation(), f);
    }
    @Test
    public void ajouterSectionCours()
    {
        Section s = new Section("Initiation reseau", 1);
        cours.ajouterSection(s);
        asssertEquals(c.getSection(), s);
    }


}