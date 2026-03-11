import future.SAE.infrastructure.mapping.CompetenceMapper;
import future.SAE.domain.model.Competence;
import future.SAE.infrastructure.persistence.CompetenceJPA;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestCompetenceMapper {

    @Autowired
    private CompetenceMapper competenceMapper;

    @Test
    @DisplayName("Devrait mapper correctement une CompetenceJPA vers le domaine")
    void testToDomain(){

        CompetenceJPA jpa = new CompetenceJPA();
        jpa.setIdCompetence(1);
        jpa.setNumero(1);
        jpa.setLibelle("Développement d'application");
        jpa.setDescription("Java");

        Competence domain = competenceMapper.toDomain(jpa);

        assertNotNull(domain);
        assertEquals(jpa.getIdCompetence(), domain.getIdCompetence());
        assertEquals(jpa.getNumero(), domain.getNumero());
        assertEquals(jpa.getLibelle(), domain.getLibelle());
        assertEquals(jpa.getDescription(), domain.getDescription());
    }

    @Test
    @DisplayName("Devrait mapper une liste de Competences")
    void testToDomainList() {

        CompetenceJPA jpa1 = new CompetenceJPA();
        jpa1.setLibelle("Competence 1");


        CompetenceJPA jpa2 = new CompetenceJPA();
        jpa2.setLibelle("Competence 2");

        List<CompetenceJPA> jpaList = List.of(jpa1, jpa2);

        List<Competence> domainList = competenceMapper.toDomainList(jpaList);

        assertEquals(2, domainList.size());
        assertEquals(jpa1.getLibelle(), domainList.getFirst().getLibelle());
        assertEquals(jpa2.getLibelle(), domainList.get(1).getLibelle());

    }

}
