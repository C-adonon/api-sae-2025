import future.SAE.SaeApplication;
import future.SAE.domain.valueObject.SuiviCours;
import future.SAE.infrastructure.mapping.SuiviCoursMapper;
import future.SAE.infrastructure.persistence.SuiviCoursJPA;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SaeApplication.class)
public class TestSuiviCoursMapper {

    @Autowired
    private SuiviCoursMapper suiviCoursMapper;

    @Test
    @DisplayName("Devrait mapper correctement un SuiviCoursJPA vers le domain")
    void testToDomain(){
        SuiviCoursJPA jpa = new SuiviCoursJPA();
        jpa.setDateDernierAcces(LocalDateTime.now());
        jpa.setProgressionGlobale(80.3f);

        SuiviCours domain = suiviCoursMapper.toDomain(jpa);

        assertNotNull(domain);
        assertEquals(jpa.getDateDernierAcces(), domain.getDateDernierAcces());
        assertEquals(jpa.getProgressionGlobale(), domain.getProgressionGlobale());
    }

    @Test
    @DisplayName("Devrait mapper correctement une liste de SuiviCoursJPA vers le domain")
    void testToDomainList(){
        SuiviCoursJPA jpa1= new SuiviCoursJPA();
        jpa1.setDateDernierAcces(LocalDateTime.now());
        jpa1.setProgressionGlobale(80.3f);

        SuiviCoursJPA jpa2 = new SuiviCoursJPA();
        jpa2.setDateDernierAcces(LocalDateTime.now().minusDays(1));
        jpa2.setProgressionGlobale(10f);

        List<SuiviCours> domainList = suiviCoursMapper.toDomainList(List.of(jpa1, jpa2));

        assertEquals(2, domainList.size());
        assertEquals(jpa1.getDateDernierAcces(), domainList.getFirst().getDateDernierAcces());
        assertEquals(jpa1.getProgressionGlobale(), domainList.getFirst().getProgressionGlobale());
        assertEquals(jpa2.getDateDernierAcces(), domainList.get(1).getDateDernierAcces());
        assertEquals(jpa2.getProgressionGlobale(), domainList.get(1).getProgressionGlobale());
    }

    @Test
    @DisplayName("Devrait mapper correctement un SuiviCours vers la persistence")
    void testToEntity(){
        SuiviCours domain = new SuiviCours();
        domain.setDateDernierAcces(LocalDateTime.now());
        domain.setProgressionGlobale(80.3f);

        SuiviCoursJPA jpa = suiviCoursMapper.toEntity(domain);

        assertNotNull(jpa);
        assertEquals(domain.getDateDernierAcces(), jpa.getDateDernierAcces());
        assertEquals(domain.getProgressionGlobale(), jpa.getProgressionGlobale());
    }

    @Test
    @DisplayName("Devrait mapper correctement une liste de SuiviCours vers la persistence")
    void testToEntityList(){
        SuiviCours domain1= new SuiviCours();
        domain1.setDateDernierAcces(LocalDateTime.now());
        domain1.setProgressionGlobale(80.3f);

        SuiviCours domain2 = new SuiviCours();
        domain2.setDateDernierAcces(LocalDateTime.now().minusDays(1));
        domain2.setProgressionGlobale(10f);

        List<SuiviCoursJPA> jpaList = suiviCoursMapper.toEntityList(List.of(domain1, domain2));

        assertEquals(2, jpaList.size());
        assertEquals(domain1.getDateDernierAcces(), jpaList.getFirst().getDateDernierAcces());
        assertEquals(domain1.getProgressionGlobale(), jpaList.getFirst().getProgressionGlobale());
        assertEquals(domain2.getDateDernierAcces(), jpaList.get(1).getDateDernierAcces());
        assertEquals(domain2.getProgressionGlobale(), jpaList.get(1).getProgressionGlobale());
    }
}
