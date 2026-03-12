import future.SAE.application.service.CompetenceService;
import future.SAE.domain.model.Competence;
import future.SAE.infrastructure.persistence.CompetenceJPA;
import future.SAE.infrastructure.repository.CompetenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestCompetenceService {

    @Mock
    private CompetenceRepository competenceRepository;

    @InjectMocks
    private CompetenceService competenceService;

    @Test
    void testTrouverParId(){

        CompetenceJPA jpa = new CompetenceJPA();
        jpa.setIdCompetence(1L);
        when(competenceRepository.findById(1L)).thenReturn(Optional.of(jpa));

        Competence result = competenceService.getById(1L);

        assertNotNull(result);
        verify(competenceRepository).findById(1L);

    }
}
