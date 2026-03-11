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
    @DisplayName("Devrait mapper correctement une Competence vers la persistence")
    void testToEntity(){

        EtatSection domain = new EtatSection();
        domain.setDateCompletion(LocalDateTime.now());

        EtatSectionJPA jpa = etatSectionMapper.toEntity(domain);

        assertEquals(domain.getDateCompletion(), jpa.getDateCompletion());

    }

}
