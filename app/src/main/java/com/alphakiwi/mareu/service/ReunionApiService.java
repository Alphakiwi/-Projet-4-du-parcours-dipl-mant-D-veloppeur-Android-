package com.alphakiwi.mareu.service;

import com.alphakiwi.mareu.Model.Reunion;

import java.util.List;


/**
 * Reunion API client
 */
public interface ReunionApiService {

    List<Reunion> getReunions();

    void deleteReunion(Reunion reunion);

    void addReunion(Reunion reunion);

    void reunionTri(Boolean salle);
}
