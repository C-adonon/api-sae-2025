package future.SAE.infrastructure.repository;

import future.SAE.domain.model.Fichier;
import future.SAE.domain.model.Section;
import future.SAE.domain.valueObject.Type;
import future.SAE.infrastructure.data.JpaFichierRepository;
import future.SAE.infrastructure.persistence.entity.FichierJPA;
import future.SAE.infrastructure.mapping.FichierMapper;
import future.SAE.infrastructure.repository.FichierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FichierRepositoryTest {

    @Mock
    private JpaFichierRepository jpaRepository;

    @Mock
    private FichierMapper mapper;

    @InjectMocks
    private FichierRepository fichierRepositoryImpl;

    private Fichier fichierDomaine;
    private FichierJPA fichierEntity;

    @BeforeEach
    void setUp() {
        fichierDomaine = new Fichier("Test", "Desc", "/path", new Section(1, "S1"), Type.PDF);
        fichierDomaine.setId(1L);

        fichierEntity = new FichierJPA();
        fichierEntity.setId(1L);
        fichierEntity.setTitre("Test");
    }

    @Test
    @DisplayName("Doit mapper le domaine en entité, sauvegarder, puis remapper en domaine")
    void sauvegarder_Succes() {
        // Arrange
        when(mapper.toEntity(fichierDomaine)).thenReturn(fichierEntity);
        when(jpaRepository.save(fichierEntity)).thenReturn(fichierEntity);
        when(mapper.toDomain(fichierEntity)).thenReturn(fichierDomaine);

        // Act
        Fichier resultat = fichierRepositoryImpl.sauvegarder(fichierDomaine);

        // Assert
        assertNotNull(resultat);
        assertEquals(1L, resultat.getId());
        verify(mapper, times(1)).toEntity(fichierDomaine);
        verify(jpaRepository, times(1)).save(fichierEntity);
        verify(mapper, times(1)).toDomain(fichierEntity);
    }

    @Test
    @DisplayName("Doit trouver un fichier par son ID et le traduire en domaine")
    void trouverParId_Trouve() {
        when(jpaRepository.findById(1L)).thenReturn(Optional.of(fichierEntity));
        when(mapper.toDomain(fichierEntity)).thenReturn(fichierDomaine);

        Optional<Fichier> resultat = fichierRepositoryImpl.trouverParId(1L);

        assertTrue(resultat.isPresent());
        assertEquals("Test", resultat.get().getTitre());
    }

    @Test
    @DisplayName("Doit retourner un Optional vide si le fichier n'est pas en BDD")
    void trouverParId_NonTrouve() {
        when(jpaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Fichier> resultat = fichierRepositoryImpl.trouverParId(99L);

        assertTrue(resultat.isEmpty());
        verify(mapper, never()).toDomain(any());
    }

    @Test
    @DisplayName("Doit demander la suppression au JpaRepository")
    void supprimer_Succes() {
        fichierRepositoryImpl.supprimer(fichierDomaine);

        verify(jpaRepository, times(1)).deleteById(1L);
    }
}