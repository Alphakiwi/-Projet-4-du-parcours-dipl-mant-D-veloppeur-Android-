package com.alphakiwi.mareu;

import com.alphakiwi.mareu.Model.Reunion;
import com.alphakiwi.mareu.di.DI;
import com.alphakiwi.mareu.service.DummyReunionGenerator;
import com.alphakiwi.mareu.service.ReunionApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;


/**
 * Unit test on Reunion service
 */
@RunWith(JUnit4.class)
public class ReunionUnitTest {

    private ReunionApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getReunionsWithSuccess() {
        List<Reunion> reunions = service.getReunions();
        List<Reunion> expectedReunions = DummyReunionGenerator.DUMMY_REUNION;
        assertThat(reunions, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedReunions.toArray()));
    }

    @Test
    public void deleteReunionWithSuccess() {
        Reunion reunionToDelete = service.getReunions().get(0);
        service.deleteReunion(reunionToDelete);
        assertFalse(service.getReunions().contains(reunionToDelete));
    }

    @Test
    public void addReunion() {

        Reunion reunionToAdd = service.getReunions().get(0);
        service.addReunion(reunionToAdd);
        assertTrue(service.getReunions().contains(reunionToAdd));

    }


    @Test
    public void getReunionsTriSalleWithSuccess() {
        List<Reunion> reunions = service.getReunions();

        service.reunionTri(true);

        List<Reunion> expectedReunions  = Arrays.asList(
                new Reunion(1, "Peach", "http://www.arthesis-diffusion.fr/fichiers_site/a1346art/contenu_pages/CIMG3247.JPG","Réunion A",  "14h00", "2019","01","01",Arrays.asList("maxime@lamzone.com","alex@lamzone.com")),
                new Reunion(3, "Daisy", "http://www.arthesis-diffusion.fr/fichiers_site/a1346art/contenu_pages/CIMG3247.JPG","Réunion A",  "14h00", "2019","04","01",Arrays.asList("maxime@lamzone.com","alex@lamzone.com")),
                new Reunion(2, "Mario", "http://www.materic.fr/images/realisations/salle-reunion-NSG-materic.jpg", "Réunion B",  "16h00","2019","01","02",Arrays.asList("paul@lamzone.com","viviane@lamzone.com")),
                new Reunion(4, "Luigi", "http://affaire.terrabotanica.fr/wp-content/uploads/2016/01/IMG_0868.jpg", "Réunion C",  "19h00","2019","01","03",Arrays.asList("amandine@lamzone.com","alex@lamzone.com"))
        );

        assertEquals(reunions, expectedReunions);

    }

    @Test
    public void getReunionsTriDateWithSuccess() {
        List<Reunion> reunions = service.getReunions();

        service.reunionTri(false);

        List<Reunion> expectedReunions  = Arrays.asList(
                new Reunion(1, "Peach", "http://www.arthesis-diffusion.fr/fichiers_site/a1346art/contenu_pages/CIMG3247.JPG","Réunion A",  "14h00", "2019","01","01",Arrays.asList("maxime@lamzone.com","alex@lamzone.com")),
                new Reunion(2, "Mario", "http://www.materic.fr/images/realisations/salle-reunion-NSG-materic.jpg", "Réunion B",  "16h00","2019","01","02",Arrays.asList("paul@lamzone.com","viviane@lamzone.com")),
                new Reunion(4, "Luigi", "http://affaire.terrabotanica.fr/wp-content/uploads/2016/01/IMG_0868.jpg", "Réunion C",  "19h00","2019","01","03",Arrays.asList("amandine@lamzone.com","alex@lamzone.com")),
                new Reunion(3, "Daisy", "http://www.arthesis-diffusion.fr/fichiers_site/a1346art/contenu_pages/CIMG3247.JPG","Réunion A",  "14h00", "2019","04","01",Arrays.asList("maxime@lamzone.com","alex@lamzone.com"))
                );

        assertEquals(reunions, expectedReunions);

    }

}


