/*
package future.SAE.application.services;

import future.SAE.domain.interfaces.ICoursRepository;
import future.SAE.domain.interfaces.ISectionRepository;
import future.SAE.domain.model.Cours;
import future.SAE.domain.model.Section;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SectionServiceTest
{
    @Mock
    private ISectionRepository sectionRepository;

    @Mock
    private ICoursRepository coursRepository;

    @InjectMocks
    private SectionService sectionService;

    private Section section;
    private Cours cours;
    private Long id;
    private Long coursId;

    @BeforeEach
    void init()
    {
        id = 1L;
        coursId = 10L;
        cours = new Cours("Maths discretes", null, null);
        section =new Section(1, "Introduction", "Texte intro");
        section.setCours(cours);
    }

    @Test
    void creerSection()
    {
        when(coursRepository.trouverParId(coursId)).thenReturn(Optional.of(cours));
        when(sectionRepository.sauvegarder(any())).thenReturn(section);

        Section res = sectionService.creerSection(1,"Introduction", "Texte intro", coursId);

        assertEquals("Introduction", res.getTitre());
        verify(sectionRepository).sauvegarder(any());
    }

    @Test
    void modifierSection()
    {
        when(sectionRepository.trouverParId(id)).thenReturn(Optional.of(section));
        when(sectionRepository.sauvegarder(any())).thenReturn(section);

        Section res = sectionService.modifierSection(id,"Intro", "Presentation de la section");

        assertEquals("Intro", res.getTitre());
        verify(sectionRepository).sauvegarder(section);
    }

    @Test
    void ouvrirSection()
    {
        Section s = new Section(1, "Java");
        when(sectionRepository.trouverParId(3L)).thenReturn(Optional.of(section));
        sectionService.ouvrirSection(3L);
        assertTrue(section.isOuverte());
        verify(sectionRepository).sauvegarder(section);
    }

    @Test
    void fermerSection()
    {
        Section s =new Section(1, "Java");
        section.setOuverte(true);
        when(sectionRepository.trouverParId(3L)).thenReturn(Optional.of(section));
        sectionService.fermerSection(3L);
        assertFalse(s.isOuverte());
        verify(sectionRepository).sauvegarder(any(future.SAE.domain.model.Section.class));
    }
}
*/
