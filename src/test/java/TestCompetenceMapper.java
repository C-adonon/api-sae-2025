import future.SAE.infrastructure.mapping.CompetenceMapper;
import future.SAE.domain.model.Competence;
import future.SAE.infrastructure.persistence.CompetenceJPA;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestCompetenceMapper {


    private final CompetenceMapper competenceMapper = Mappers.getMapper(CompetenceMapper.class);

    @Test
    @DisplayName("Devrait mapper correctement une CompetenceJPA vers le domaine")
    void testToDomain(){

        CompetenceJPA jpa = new CompetenceJPA();
        jpa.setIdCompetence(1L);
        jpa.setNumero(1);
        jpa.setLibelle("Développement d'application");
        jpa.setDescription("Java");

        Competence domain = this.competenceMapper.toDomain(jpa);

        assertNotNull(domain);
        assertEquals(jpa.getIdCompetence(), domain.getIdCompetence());
        assertEquals(jpa.getNumero(), domain.getNumero());
        assertEquals(jpa.getLibelle(), domain.getLibelle());
        assertEquals(jpa.getDescription(), domain.getDescription());
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

        assertEquals(2, domainList.size());
        assertEquals(jpa1.getLibelle(), domainList.getFirst().getLibelle());
        assertEquals(jpa2.getLibelle(), domainList.get(1).getLibelle());

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

        assertNotNull(jpa);
        assertEquals(domain.getIdCompetence(), jpa.getIdCompetence());
        assertEquals(domain.getNumero(), jpa.getNumero());
        assertEquals(domain.getLibelle(), jpa.getLibelle());
        assertEquals(domain.getDescription(), jpa.getDescription());
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

        assertEquals(2, jpaList.size());
        assertEquals(domain1.getLibelle(), jpaList.getFirst().getLibelle());
        assertEquals(domain2.getLibelle(), jpaList.get(1).getLibelle());

    }

}
