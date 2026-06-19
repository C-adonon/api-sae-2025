/*
package future.SAE.application.services;

import future.SAE.domain.interfaces.IFormationRepository;
import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Formation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FormationServiceTest {
    @Mock
    private IFormationRepository formationRepository;

    @InjectMocks
    private FormationService formationService;

    @Test
    void creerFormation() {
        Formation f = new Formation();
        f.setNom("BD");
        f.setAnnee(2);
        when(formationRepository.sauvegarder(any(Formation.class))).thenReturn(f);

        Formation res = formationService.creerFormation(2, "BD");

        assertEquals("BD", res.getNom());
        verify(formationRepository).sauvegarder(any(Formation.class));
    }

    @Test
    void accederFormation() {
        Formation f = new Formation();
        f.setNom(("BD"));
        when(formationRepository.trouverParId(1L)).thenReturn(Optional.of(f));

        Formation res = formationService.accederFormation(1L);

        assertEquals("BD", res.getNom());
    }

    @Test
    void listerFormation() {
        List<Formation> formations = List.of(new Formation(), new Formation());
        when(formationRepository.trouverToutes()).thenReturn(formations);

        List<Formation> res = formationService.listerFormation();

        assertEquals(2, res.size());
    }

    @Test
    void ajouterCours() {
        Formation f = new Formation();
        Cours c = new Cours("BD", null, null);
        when(formationRepository.trouverParId(1L)).thenReturn(Optional.of(f));
        formationService.ajouterCours(1L, c);

        assertTrue(f.getCours().contains(c));
        verify(formationRepository).sauvegarder(f);
    }

    @Test
    void supprimerFormation()
    {
        Formation f = new Formation();
        Cours c = new Cours("BD", null, null);
        f.ajouterCours(c);
        when(formationRepository.trouverParId(1L)).thenReturn(Optional.of(f));
        formationService.supprimerCours(1L, c);
        assertFalse(f.getCours().contains(c));
        verify(formationRepository).sauvegarder(f);
    }
}

*/
