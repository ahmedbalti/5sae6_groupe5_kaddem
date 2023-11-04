package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EquipeServiceImplTest {

    @Autowired
    private EquipeServiceImpl equipeService;

    @MockBean
    private EquipeRepository equipeRepository;

    @Test
    public void testRetrieveAllEquipes() {
        // Créez une liste simulée d'équipes
        List<Equipe> equipesSimulees = new ArrayList<>();
        equipesSimulees.add(new Equipe(1, "Equipe 1", Niveau.JUNIOR));
        equipesSimulees.add(new Equipe(2, "Equipe 2", Niveau.SENIOR));
        // Ajoutez d'autres équipes simulées au besoin

        // Configurez le comportement simulé du repository
        when(equipeRepository.findAll()).thenReturn(equipesSimulees);

        // Appelez la méthode de service à tester
        List<Equipe> equipes = equipeService.retrieveAllEquipes();

        // Vérifiez le résultat avec des assertions
        assertNotNull(equipes);
        assertEquals(2, equipes.size()); // Vérifiez le nombre d'équipes simulées

        // Effectuez d'autres assertions selon le comportement attendu
    }

    @Test
    public void testAddEquipe() {
        // Créez une équipe simulée
        Equipe equipeSimulee = new Equipe(1, "Equipe Test", Niveau.JUNIOR);

        // Configurez le comportement simulé du repository
        when(equipeRepository.save(any(Equipe.class))).thenReturn(equipeSimulee);

        // Appelez la méthode de service à tester
        Equipe equipeAjoutee = equipeService.addEquipe(equipeSimulee);

        // Vérifiez le résultat avec des assertions
        assertNotNull(equipeAjoutee);
        assertEquals(equipeSimulee.getIdEquipe(), equipeAjoutee.getIdEquipe());
        assertEquals(equipeSimulee.getNomEquipe(), equipeAjoutee.getNomEquipe());

        // Effectuez d'autres assertions selon le comportement attendu
    }

    @Test
    public void testUpdateEquipe() {
        // Créez une équipe simulée avec des propriétés modifiées
        Equipe equipeSimulee = new Equipe(1, "Equipe Modifiée", Niveau.SENIOR);

        // Configurez le comportement simulé du repository
        when(equipeRepository.save(any(Equipe.class))).thenReturn(equipeSimulee);

        // Appelez la méthode de service à tester
        Equipe equipeModifiee = equipeService.updateEquipe(equipeSimulee);

        // Vérifiez le résultat avec des assertions
        assertNotNull(equipeModifiee);
        assertEquals(equipeSimulee.getIdEquipe(), equipeModifiee.getIdEquipe());
        assertEquals(equipeSimulee.getNomEquipe(), equipeModifiee.getNomEquipe());
        assertEquals(equipeSimulee.getNiveau(), equipeModifiee.getNiveau());

        // Effectuez d'autres assertions selon le comportement attendu
    }

    @Test
    public void testDeleteEquipe() {
        // Créez une équipe simulée
        Equipe equipeSimulee = new Equipe(1, "Equipe à Supprimer", Niveau.JUNIOR);

        // Configurez le comportement simulé du repository pour renvoyer l'équipe simulée lors de la recherche
        when(equipeRepository.findById(eq(1))).thenReturn(java.util.Optional.of(equipeSimulee));

        // Appelez la méthode de service à tester
        equipeService.deleteEquipe(1);

        // Vérifiez que la méthode delete a été appelée avec l'équipe simulée
        verify(equipeRepository, times(1)).delete(equipeSimulee);

        // Effectuez d'autres assertions selon le comportement attendu
    }
    // Écrivez d'autres méthodes de test pour les autres fonctionnalités
}
