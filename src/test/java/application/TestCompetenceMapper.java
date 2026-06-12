package application;

import future.SAE.SaeApplication;
import future.SAE.infrastructure.mapping.CompetenceMapper;
import future.SAE.domain.model.Competence;
import future.SAE.infrastructure.persistence.CompetenceJPA;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest(classes = SaeApplication.class)
public class TestCompetenceMapper {

    @Autowired
    private CompetenceMapper competenceMapper;

    @Test
    @DisplayName("Devrait mapper correctement une CompetenceJPA vers le domaine")
    void testToDomain(){

        CompetenceJPA jpa = new CompetenceJPA();
        jpa.setIdCompetence(1L);
        jpa.setNumero(1);
        jpa.setLibelle("Développement d'application");
        jpa.setDescription("Java");

        Competence domain = this.competenceMapper.toDomain(jpa);

        Assert.isTrue(domain != null, "Le domaine ne doit pas être null");
        Assert.isTrue(jpa.getIdCompetence().equals(domain.getIdCompetence()), "Les id doivent être égaux");
        Assert.isTrue(jpa.getNumero() == domain.getNumero(), "Les numéros doivent être égaux");
        Assert.isTrue(jpa.getLibelle().equals(domain.getLibelle()), "Les libellés doivent être égaux");
        Assert.isTrue(jpa.getDescription().equals(domain.getDescription()), "Les descriptions doivent être égales");
    }

    @Test
    @DisplayName("Devrait mapper une liste de CompetenceJPA vers le domaine")
    void testToDomainList() {

        CompetenceJPA jpa1 = new CompetenceJPA();
        jpa1.setLibelle("Competence 1");

        CompetenceJPA jpa2 = new CompetenceJPA();
        jpa2.setLibelle("Competence 2");

        List<CompetenceJPA> jpaList = List.of(jpa1, jpa2);
        List<Competence> domainList = competenceMapper.toDomainList(jpaList);

        Assert.isTrue(domainList.size() == 2, "La liste doit contenir 2 éléments");
        Assert.isTrue(jpa1.getLibelle().equals(domainList.getFirst().getLibelle()), "Le premier libellé doit correspondre");
        Assert.isTrue(jpa2.getLibelle().equals(domainList.get(1).getLibelle()), "Le deuxième libellé doit correspondre");
    }

    @Test
    @DisplayName("Devrait mapper correctement une Competence vers la persistence")
    void testToEntity(){

        Competence domain = new Competence();
        domain.setIdCompetence(1L);
        domain.setNumero(1);
        domain.setLibelle("Développement d'application");
        domain.setDescription("Java");

        CompetenceJPA jpa = competenceMapper.toEntity(domain);

        Assert.isTrue(jpa != null, "L'entité ne doit pas être null");
        Assert.isTrue(domain.getIdCompetence().equals(jpa.getIdCompetence()), "Les id doivent être égaux");
        Assert.isTrue(domain.getNumero() == jpa.getNumero(), "Les numéros doivent être égaux");
        Assert.isTrue(domain.getLibelle().equals(jpa.getLibelle()), "Les libellés doivent être égaux");
        Assert.isTrue(domain.getDescription().equals(jpa.getDescription()), "Les descriptions doivent être égales");
    }

    @Test
    @DisplayName("Devrait mapper une liste de Competence vers la persistence")
    void testToEntityList() {

        Competence domain1 = new Competence();
        domain1.setLibelle("Competence 1");

        Competence domain2 = new Competence();
        domain2.setLibelle("Competence 2");

        List<Competence> domainList = List.of(domain1, domain2);
        List<CompetenceJPA> jpaList = competenceMapper.toEntityList(domainList);

        Assert.isTrue(jpaList.size() == 2, "La liste doit contenir 2 éléments");
        Assert.isTrue(domain1.getLibelle().equals(jpaList.getFirst().getLibelle()), "Le premier libellé doit correspondre");
        Assert.isTrue(domain2.getLibelle().equals(jpaList.get(1).getLibelle()), "Le deuxième libellé doit correspondre");
    }
}