package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;

public class EquipeServiceImplTest {

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Mock
    private EquipeRepository equipeRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddEquipe() {
        Equipe equipeToAdd = new Equipe("Equipe de Test");
        when(equipeRepository.save(equipeToAdd)).thenReturn(equipeToAdd);

        Equipe addedEquipe = equipeService.addEquipe(equipeToAdd);

        assertEquals(equipeToAdd, addedEquipe);
    }

    @Test
    public void testUpdateEquipe() {
        Equipe equipeToUpdate = new Equipe(1, "Equipe existante", Niveau.JUNIOR);
        when(equipeRepository.save(equipeToUpdate)).thenReturn(equipeToUpdate);

        Equipe updatedEquipe = equipeService.updateEquipe(equipeToUpdate);

        assertEquals(equipeToUpdate, updatedEquipe);
    }

    @Test
    public void testRetrieveEquipe() {
        Equipe equipeToRetrieve = new Equipe(1, "Equipe existante", Niveau.JUNIOR);
        when(equipeRepository.findById(1)).thenReturn(java.util.Optional.of(equipeToRetrieve));

        Equipe retrievedEquipe = equipeService.retrieveEquipe(1);

        assertEquals(equipeToRetrieve, retrievedEquipe);
    }

    @Test
    public void testDeleteEquipe() {
        Equipe equipeToDelete = new Equipe(1, "Equipe existante", Niveau.JUNIOR);
        when(equipeRepository.findById(1)).thenReturn(java.util.Optional.of(equipeToDelete));

        equipeService.deleteEquipe(1);

        verify(equipeRepository).delete(equipeToDelete);
    }
}
