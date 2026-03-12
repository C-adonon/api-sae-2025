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

import java.util.List;
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
    void testGetById(){

        CompetenceJPA jpa = new CompetenceJPA();
        jpa.setIdCompetence(1L);
        when(this.competenceRepository.findById(1L)).thenReturn(Optional.of(jpa));

        Competence result = this.competenceService.getCompetenceById(1L);

        assertNotNull(result);
        verify(this.competenceRepository).findById(1L);

    }

    @Test
    void testGetAllCompetences(){
        List<CompetenceJPA> jpaList = List.of(new CompetenceJPA(), new CompetenceJPA());
        List<Competence> domainList = List.of(new Competence(), new Competence());

        when(competenceRepository.findAll()).thenReturn(jpaList);
        when(competenceMapper.toDomainList(jpaList)).thenReturn(domainList);

        List<Competence> result = competenceService.getAllCompetences();

        assertEquals(2, result.size());
        verify(competenceRepository).findAll();
    }

    @Test
    void testGetCompetenceByIdFormation() {
        Long idFormation = 1L;
        List<CompetenceJPA> jpaList = List.of(new CompetenceJPA());
        List<Competence> domainList = List.of(new Competence());

        when(competenceRepository.findByFormation_Id(idFormation)).thenReturn(jpaList);
        when(competenceMapper.toDomainList(jpaList)).thenReturn(domainList);

        List<Competence> result = competenceService.getCompetenceByIdFormation(idFormation);

        assertFalse(result.isEmpty());
        verify(competenceRepository).findByFormation_Id(idFormation);
    }

    @Test
    void testCreateCompetence() {

        Competence competence = new Competence();
        CompetenceJPA jpa = new CompetenceJPA();

        when(this.competenceMapper.toEntity(competence)).thenReturn(jpa);
        when(this.competenceRepository.save(jpa)).thenReturn(jpa);
        when(this.competenceMapper.toDomain(jpa)).thenReturn(competence);

        Competence result = this.competenceService.createCompetence(competence);

        assertNotNull(result);
        verify(this.competenceRepository).save(jpa);

    }

    @Test
    void testUpdateCompetence_Success() {
        Long id = 1L;
        Competence newCompetence = new Competence();
        newCompetence.setNumero(10);
        newCompetence.setLibelle("Nouveau");

        CompetenceJPA existingJPA = new CompetenceJPA();
        existingJPA.setIdCompetence(id);

        when(competenceRepository.findById(id)).thenReturn(Optional.of(existingJPA));
        when(competenceRepository.save(any(CompetenceJPA.class))).thenReturn(existingJPA);
        when(competenceMapper.toDomain(existingJPA)).thenReturn(newCompetence);

        Competence result = competenceService.updateCompetence(id, newCompetence);

        assertNotNull(result);
        assertEquals("Nouveau", result.getLibelle());
        verify(competenceRepository).save(existingJPA);
    }

    @Test
    void testUpdateCompetence_NotFound() {
        Long id = 1L;
        when(competenceRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                competenceService.updateCompetence(id, new Competence())
        );
        verify(competenceRepository, never()).save(any());
    }

    @Test
    void testDeleteCompetence_Success() {
        Long id = 1L;
        when(competenceRepository.existsById(id)).thenReturn(true);

        competenceService.deleteCompetence(id);

        verify(competenceRepository).deleteById(id);
    }

    @Test
    void testDeleteCompetence_NotFound() {
        Long id = 1L;
        when(competenceRepository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () ->
                competenceService.deleteCompetence(id)
        );
        verify(competenceRepository, never()).deleteById(any());
    }

}
