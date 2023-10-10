package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UniversiteServiceImplTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllUniversites() {
        // Créez une liste factice d'universités pour simuler la réponse de la base de données.
        List<Universite> universites = new ArrayList<>();
        universites.add(new Universite(1, "Universite 1"));
        universites.add(new Universite(2, "Universite 2"));

        // Configurez le comportement simulé du repository.
        when(universiteRepository.findAll()).thenReturn(universites);

        // Appelez la méthode à tester.
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Vérifiez que la méthode renvoie la liste simulée.
        assertEquals(2, result.size());
    }


    @Test
    void testAddUniversite() {
        // Créez une instance factice d'Universite.
        Universite universite = new Universite(1, "Universite 1");

        // Configurez le comportement simulé du repository.
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Appelez la méthode à tester.
        Universite result = universiteService.addUniversite(universite);

        // Vérifiez que la méthode renvoie l'objet Universite simulé.
        assertNotNull(result);
        assertEquals(universite.getIdUniv(), result.getIdUniv());
        assertEquals(universite.getNomUniv(), result.getNomUniv());
    }


    @Test
    void testUpdateUniversite() {
        // Créez une instance factice d'Universite avec des modifications.
        Universite universite = new Universite(1, "Universite Modifiée");

        // Configurez le comportement simulé du repository.
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Appelez la méthode à tester.
        Universite result = universiteService.updateUniversite(universite);

        // Vérifiez que la méthode renvoie l'objet Universite simulé.
        assertNotNull(result);
        assertEquals(universite.getIdUniv(), result.getIdUniv());
        assertEquals(universite.getNomUniv(), result.getNomUniv());
    }

    @Test
    void testDeleteUniversite() {
        // Créez une instance factice d'Universite.
        Universite universite = new Universite(1, "Universite 1");

        // Configurez le comportement simulé du repository.
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        // Appelez la méthode à tester.
        universiteService.deleteUniversite(1);

        // Vérifiez que la méthode a été appelée avec l'ID correct.
        verify(universiteRepository, times(1)).delete(universite);
    }
    
}

