/*
package future.SAE.application.services;

import future.SAE.domain.interfaces.ICoursRepository;
import future.SAE.domain.model.Cours;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CoursServiceTest
{
    @Mock
    private ICoursRepository coursRepository;

    @InjectMocks
    private CoursService coursService;

    @Test
    void creerCours()
    {
        Cours c = new Cours("Java", null, null);
        when(coursRepository.sauvegarder(any(Cours.class))).thenReturn(c);

        Cours res = coursService.creerCours("Java");

        assertThat(res.getNom()).isEqualTo("Java");
        verify(coursRepository).sauvegarder(any(Cours.class));
    }

    @Test
    void modifierCours()
    {
        Cours c = new Cours("Java", null, null);
        when(coursRepository.trouverParId(1L)).thenReturn(Optional.of(c));
        when(coursRepository.sauvegarder(any(Cours.class))).thenReturn(c);

        Cours res = coursService.modifierCours(1L, "Spring");

        assertThat(res.getNom()).isEqualTo("Spring");
        verify(coursRepository).sauvegarder(c);
    }

    @Test
    void accederCours()
    {
        Cours c = new Cours("Java", null, null);
        when(coursRepository.trouverParId(1L)).thenReturn(Optional.of(c));

        Cours res = coursService.accederCours(1L);

        assertThat(res.getNom()).isEqualTo("Java");
    }

    @Test
    void listerCours()
    {
        List<Cours> c = List.of(new Cours("Java", null, null), new Cours("Python", null, null));
        when(coursRepository.trouverTous()).thenReturn(c);

        List<Cours> res = coursService.listerCours();

        assertThat(res).hasSize(2);
        verify(coursRepository).trouverTous();
    }

    @Test
    void supprimerCours()
    {
        Cours c = new Cours("Java", null, null);
        when(coursRepository.trouverParId(1L)).thenReturn(Optional.of(c));
        coursService.supprimerCours(1L);
        verify(coursRepository).supprimer(c);
    }
}
*/
