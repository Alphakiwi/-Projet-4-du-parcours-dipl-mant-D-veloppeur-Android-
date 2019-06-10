package com.alphakiwi.mareu.events;


import com.alphakiwi.mareu.Model.Reunion;

public class AddReunionEvent {


    public Reunion reunion;

    /**
     * Constructor.
     * @param reunion
     */
    public AddReunionEvent(Reunion reunion) {

        this.reunion = reunion;
    }

}
