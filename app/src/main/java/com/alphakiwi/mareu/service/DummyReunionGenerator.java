package com.alphakiwi.mareu.service;

import com.alphakiwi.mareu.Model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class DummyReunionGenerator {

    public static List<Reunion> DUMMY_REUNION = Arrays.asList(
            new Reunion(1, "Peach", "http://www.arthesis-diffusion.fr/fichiers_site/a1346art/contenu_pages/CIMG3247.JPG","Réunion A",  "14h00", "2019","01","01",Arrays.asList("maxime@lamzone.com","alex@lamzone.com")),
            new Reunion(2, "Mario", "http://www.materic.fr/images/realisations/salle-reunion-NSG-materic.jpg", "Réunion B",  "16h00","2019","01","02",Arrays.asList("paul@lamzone.com","viviane@lamzone.com")),
            new Reunion(3, "Daisy", "http://www.arthesis-diffusion.fr/fichiers_site/a1346art/contenu_pages/CIMG3247.JPG","Réunion A",  "14h00", "2019","04","01",Arrays.asList("maxime@lamzone.com","alex@lamzone.com")),
            new Reunion(4, "Luigi", "http://affaire.terrabotanica.fr/wp-content/uploads/2016/01/IMG_0868.jpg", "Réunion C",  "19h00","2019","01","03",Arrays.asList("amandine@lamzone.com","alex@lamzone.com"))
    );



    static List<Reunion> generateReunions() {
        return new ArrayList<>(DUMMY_REUNION );
    }

}
