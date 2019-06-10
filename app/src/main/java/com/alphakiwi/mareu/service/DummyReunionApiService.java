package com.alphakiwi.mareu.service;


import com.alphakiwi.mareu.Model.Reunion;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyReunionApiService implements ReunionApiService {

    private List<Reunion> reunions = DummyReunionGenerator.generateReunions();


    @Override
    public List<Reunion> getReunions() {
        return reunions;
    }


    @Override
    public void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }



    @Override
    public void addReunion(Reunion reunion) {
        reunions.add(reunion);
    }

    @Override
    public void reunionTri(Boolean salle) {

        Comparator<Reunion> comparator;

        if (salle) {
             comparator = Comparator.comparing(Reunion::getLieu);
        }else{
             comparator = Comparator.comparing(Reunion::getDateAnnee).thenComparing(Reunion::getDateMois).thenComparing(Reunion::getDateJour).thenComparing(Reunion::getHour);

        }
        Collections.sort(reunions, comparator);


    }

}
