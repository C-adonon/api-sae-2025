import future.SAE.application.service.CompetenceService;
import future.SAE.domain.model.Competence;
import future.SAE.infrastructure.mapping.CompetenceMapper;
import future.SAE.infrastructure.persistence.CompetenceJPA;
import future.SAE.infrastructure.repository.CompetenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestCompetenceService {

    @Mock
    private CompetenceRepository competenceRepository;

    @Mock
    private CompetenceMapper competenceMapper;

    @InjectMocks
    private CompetenceService competenceService;

    @Test
    void testFindById(){

        CompetenceJPA jpa = new CompetenceJPA();
        jpa.setIdCompetence(1L);
        when(this.competenceRepository.findById(1L)).thenReturn(Optional.of(jpa));

        Competence result = this.competenceService.getCompetenceById(1L);

        assertNotNull(result);
        verify(this.competenceRepository).findById(1L);

    }

    @Test
    void testCreerCompetence() {

        Competence competence = new Competence();
        CompetenceJPA jpa = new CompetenceJPA();

        when(this.competenceMapper.toEntity(competence)).thenReturn(jpa);
        when(this.competenceRepository.save(jpa)).thenReturn(jpa);
        when(this.competenceMapper.toDomain(jpa)).thenReturn(competence);

        Competence result = this.competenceService.createCompetence(competence);

        assertNotNull(result);
        verify(this.competenceRepository).save(jpa);

    }


}
