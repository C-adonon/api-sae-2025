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
    @DisplayName("Devrait mapper une liste d'EtatSection en JPA")
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



}
