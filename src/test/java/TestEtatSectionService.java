import future.SAE.application.service.EtatSectionService;
import future.SAE.domain.valueObject.EtatSection;
import future.SAE.infrastructure.mapping.EtatSectionMapper;
import future.SAE.infrastructure.persistence.EtatSectionJPA;
import future.SAE.infrastructure.repository.EtatSectionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEtatSectionService {

    @Mock
    private EtatSectionRepository etatSectionRepository;

    @Mock
    private EtatSectionMapper etatSectionMapper;

    @InjectMocks
    private EtatSectionService etatSectionService;

    @Test
    void testCreateEtatSection() {
        EtatSection domain = new EtatSection();
        EtatSectionJPA jpa = new EtatSectionJPA();

        when(etatSectionMapper.toEntity(domain)).thenReturn(jpa);
        when(etatSectionRepository.save(jpa)).thenReturn(jpa);
        when(etatSectionMapper.toDomain(jpa)).thenReturn(domain);

        EtatSection result = etatSectionService.createEtatSection(domain);

        assertNotNull(result);
        verify(etatSectionRepository).save(jpa);
    }

    @Test
    void testGetEtatSectionById_Success() {
        Long id = 1L;
        EtatSectionJPA jpa = new EtatSectionJPA();
        EtatSection domain = new EtatSection();

        when(etatSectionRepository.findById(id)).thenReturn(Optional.of(jpa));
        when(etatSectionMapper.toDomain(jpa)).thenReturn(domain);

        EtatSection result = etatSectionService.getEtatSectionById(id);

        assertNotNull(result);
        verify(etatSectionRepository).findById(id);
    }

    @Test
    void testGetEtatSectionById_NotFound() {
        Long id = 1L;
        when(etatSectionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> etatSectionService.getEtatSectionById(id));
        verify(etatSectionMapper, never()).toDomain(any(EtatSectionJPA.class));
    }

    @Test
    void testGetAllEtatSection() {
        List<EtatSectionJPA> jpaList = List.of(new EtatSectionJPA(), new EtatSectionJPA());
        List<EtatSection> domainList = List.of(new EtatSection(), new EtatSection());

        when(etatSectionRepository.findAll()).thenReturn(jpaList);
        when(etatSectionMapper.toDomainList(jpaList)).thenReturn(domainList);

        List<EtatSection> result = etatSectionService.getAllEtatSection();

        assertEquals(2, result.size());
        verify(etatSectionRepository).findAll();
    }

    @Test
    void testGetEtatSectionByEleve() {
        UUID idEleve = UUID.randomUUID();
        List<EtatSectionJPA> jpaList = List.of(new EtatSectionJPA());
        List<EtatSection> domainList = List.of(new EtatSection());

        when(etatSectionRepository.findByEleve_IdUser(idEleve)).thenReturn(jpaList);
        when(etatSectionMapper.toDomainList(jpaList)).thenReturn(domainList);

        List<EtatSection> result = etatSectionService.getEtatSectionByEleve(idEleve);

        assertFalse(result.isEmpty());
        verify(etatSectionRepository).findByEleve_IdUser(idEleve);
    }

    @Test
    void testGetEtatSectionByEleveAndEstTermineeTrue() {
        UUID idEleve = UUID.randomUUID();
        List<EtatSectionJPA> jpaList = List.of(new EtatSectionJPA());
        List<EtatSection> domainList = List.of(new EtatSection());

        when(etatSectionRepository.findByEleve_IdUserAndEstTermineeTrue(idEleve)).thenReturn(jpaList);
        when(etatSectionMapper.toDomainList(jpaList)).thenReturn(domainList);

        List<EtatSection> result = etatSectionService.getEtatSectionByEleveAndEstTermineeTrue(idEleve);

        assertFalse(result.isEmpty());
        verify(etatSectionRepository).findByEleve_IdUserAndEstTermineeTrue(idEleve);
    }

    @Test
    void testValiderSectionTerminee_Success() {
        Long id = 1L;
        EtatSectionJPA jpa = new EtatSectionJPA();
        // On mocke un objet Domaine car la méthode "marquerCommeTerminee" va être appelée dessus
        EtatSection domain = mock(EtatSection.class);

        when(etatSectionRepository.findById(id)).thenReturn(Optional.of(jpa));
        when(etatSectionMapper.toDomain(jpa)).thenReturn(domain);
        when(etatSectionMapper.toEntity(domain)).thenReturn(jpa);
        when(etatSectionRepository.save(jpa)).thenReturn(jpa);
        when(etatSectionMapper.toDomain(jpa)).thenReturn(domain);

        EtatSection result = etatSectionService.validerSectionTerminee(id);

        assertNotNull(result);
        // Vérifie que les méthodes métier du domaine ont bien été déclenchées
        verify(domain).marquerCommeTerminee();
        verify(domain).setDateCompletion(any());
        verify(etatSectionRepository).save(jpa);
    }

    @Test
    void testValiderSectionTerminee_NotFound() {
        Long id = 1L;
        when(etatSectionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> etatSectionService.validerSectionTerminee(id));
        verify(etatSectionRepository, never()).save(any());
    }

    @Test
    void testDeleteEtatSection_Success() {
        Long id = 1L;
        when(etatSectionRepository.existsById(id)).thenReturn(true);

        etatSectionService.deleteEtatSection(id);

        verify(etatSectionRepository).deleteById(id);
    }

    @Test
    void testDeleteEtatSection_NotFound() {
        Long id = 1L;
        when(etatSectionRepository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> etatSectionService.deleteEtatSection(id));
        verify(etatSectionRepository, never()).deleteById(id);
    }

}
