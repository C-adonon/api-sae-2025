package future.SAE.application.services;

import future.SAE.application.exception.FichierIntrouvableException;
import future.SAE.application.interfaces.IFichierService;
import future.SAE.domain.interfaces.IFichierRepository;
import future.SAE.domain.model.Fichier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.plaf.FileChooserUI;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FichierServiceTest
{
    @Mock
    private IFichierRepository fichierRepository;

    @InjectMocks
    private FichierService fichierService;

    @Test
    void creerFichier()
    {
        Fichier f = new Fichier();
        f.setTitre("Cours Java");
        when(fichierRepository.sauvegarder(any(Fichier.class))).thenReturn(f);

        Fichier res = fichierService.creerFichier("Cours Java", "Introduction au cours", "/home/java/java.pdf");

        assertEquals("Cours Java", res.getTitre());
        verify(fichierRepository).sauvegarder(any(Fichier.class));
    }

    @Test
    void accederFichier()
    {
        Fichier f = new Fichier();
        f.setTitre("Cours Java");
        when(fichierRepository.trouverParId(1L)).thenReturn(Optional.of(f));

        Fichier res = fichierService.accederFichier(1L);
        assertEquals("Cours Java", res.getTitre());
    }

    @Test
    void listerFichiers()
    {
        List<Fichier> fichiers = List.of(new Fichier(), new Fichier());
        when(fichierRepository.trouverTous()).thenReturn(fichiers);
        List<Fichier> res = fichierService.listerFichier();
        assertEquals(2, res.size());
    }

    @Test
    void modifierFichier()
    {
        Fichier f = new Fichier();
        f.setTitre("Cours Java");
        when(fichierRepository.trouverParId(1L)).thenReturn(Optional.of(f));
        when(fichierRepository.sauvegarder(any(Fichier.class))).thenReturn(f);

        Fichier res = fichierService.modifierFichier(1L, "Java", "Cours Java");

        assertEquals("Java", res.getTitre());
        verify(fichierRepository).sauvegarder(f);
    }

    @Test
    void supprimerFichier()
    {
        Fichier f = new Fichier();
        when(fichierRepository.trouverParId(1L)).thenReturn(Optional.of(f));
        fichierService.supprimerFichier(1L);
        verify(fichierRepository).supprimer(f);
    }

}
