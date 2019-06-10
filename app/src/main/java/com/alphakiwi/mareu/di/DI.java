package com.alphakiwi.mareu.di;


import com.alphakiwi.mareu.Model.Reunion;
import com.alphakiwi.mareu.service.DummyReunionApiService;
import com.alphakiwi.mareu.service.ReunionApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static ReunionApiService service = new DummyReunionApiService();


    public static ReunionApiService getReunionApiService() {
        return service;
    }


    public static ReunionApiService getNewInstanceApiService() {
        return new DummyReunionApiService();
    }
}
