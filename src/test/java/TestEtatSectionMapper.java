import future.SAE.infrastructure.mapping.EtatSectionMapper;
import future.SAE.domain.valueObject.EtatSection;
import future.SAE.infrastructure.persistence.EleveJPA;
import future.SAE.infrastructure.persistence.EtatSectionJPA;
import future.SAE.infrastructure.persistence.SectionJPA;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestEtatSectionMapper {

    @Autowired
    EtatSectionMapper etatSectionMapper;

    @Test
    @DisplayName("Devrait mapper correctement un EtatSection en Entity")
    void testToEntity(){

        EtatSection domain = new EtatSection();
        domain.setIdEtatSection(1L);
        domain.setDateCompletion(LocalDateTime.now());

        EtatSectionJPA jpa = etatSectionMapper.toEntity(domain);

        assertNotNull(jpa);
        assertEquals(domain.getIdEtatSection(), jpa.getIdEtatSection());
        assertEquals(domain.getDateCompletion(), jpa.getDateCompletion());
    }

    @Test
    @DisplayName("Devrait mapper une liste d'EtatSection en Entity")
    void testToEntityList(){
        EtatSection domain1 = new EtatSection();
        domain1.setIdEtatSection(1L);
        domain1.setDateCompletion(LocalDateTime.now());

        EtatSection domain2 = new EtatSection();
        domain2.setIdEtatSection(2L);
        domain2.setDateCompletion(LocalDateTime.now().minusDays(1));

        List<EtatSectionJPA> jpaList = etatSectionMapper.toEntityList(List.of(domain1, domain2));

        assertEquals(2, jpaList.size());
        assertEquals(domain1.getIdEtatSection(), jpaList.getFirst().getIdEtatSection());
        assertEquals(domain1.getDateCompletion(), jpaList.get(1).getDateCompletion());
        assertEquals(domain2.getIdEtatSection(), jpaList.getFirst().getIdEtatSection());
        assertEquals(domain2.getDateCompletion(), jpaList.get(2).getDateCompletion());

    }

    @Test
    @DisplayName("Devrait mapper correctement un EtatSectionJPA vers le domain")
    void testToDomain(){

        EtatSectionJPA jpa = new EtatSectionJPA();
        jpa.setIdEtatSection(1L);
        jpa.setDateCompletion(LocalDateTime.now());

        EtatSection domain = etatSectionMapper.toDomain(jpa);

        assertNotNull(domain);
        assertEquals(jpa.getIdEtatSection(), domain.getIdEtatSection());
        assertEquals(jpa.getDateCompletion(), domain.getDateCompletion());
    }

    @Test
    @DisplayName("Devrait mapper une liste d'EtatSectionJPA vers le domain")
    void testToDomainList(){
        EtatSectionJPA jpa1 = new EtatSectionJPA();
        jpa1.setIdEtatSection(1L);
        jpa1.setDateCompletion(LocalDateTime.now());

        EtatSectionJPA jpa2 = new EtatSectionJPA();
        jpa2.setIdEtatSection(2L);
        jpa2.setDateCompletion(LocalDateTime.now().minusDays(1));

        List<EtatSection> domainList = etatSectionMapper.toDomainList(List.of(jpa1, jpa2));

        assertEquals(2, domainList.size());
        assertEquals(jpa1.getIdEtatSection(), domainList.getFirst().getIdEtatSection());
        assertEquals(jpa1.getDateCompletion(), domainList.get(1).getDateCompletion());
        assertEquals(jpa2.getIdEtatSection(), domainList.getFirst().getIdEtatSection());
        assertEquals(jpa2.getDateCompletion(), domainList.get(2).getDateCompletion());

    }



}
