import future.SAE.domain.model.Section;

public class SectionTest {
    public static void main() {
        System.out.print("TEST START\n");

        // TEST CONSTRUCTEURS
        Section s1 = new Section(1, "Introduction");
        System.out.print(s1.toString() + "\n");
        Section s2 = new Section(2, "Section 2", "Le texte de la section 2");
        System.out.print(s2.toString() + "\n");
        Section s3 = new Section(3, "Section 3", "Le texte de la section 3", true);
        System.out.print(s3.toString() + "\n");

        // TEST FERMER/OUVRIR SECTION
        s1.ouvrirSection();
        System.out.print("La section 1 est ouverte : " + s1.getOuverte() + "\n");
        s3.fermerSection();
        System.out.print("La section 3 est ouverte: " + s3.getOuverte() + "\n");

        System.out.print("TEST END\n");
    }
}
