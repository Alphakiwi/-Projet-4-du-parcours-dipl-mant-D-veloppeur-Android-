package com.alphakiwi.mareu.Model;

import java.util.List;
import java.util.Objects;

/**
 * Model object representing a Reunion
 */
public class Reunion {

    /** Identifier */
    private Integer id;

    private String subject;

    private String imageUrl;

    private String lieu;

    private String hour;

    private String dateAnnee;

    private String dateMois;

    private String dateJour;

    private List<String> adressesMail;




    public Reunion(Integer id, String subject, String imageUrl, String lieu, String hour, String dateAnnee,String dateMois,String dateJour, List<String> adressesMail) {
        this.id = id;
        this.subject = subject;
        this.imageUrl = imageUrl;
        this.lieu = lieu;
        this.hour = hour;
        this.dateAnnee =  dateAnnee;
        this.dateMois =  dateMois;
        this.dateJour =  dateJour;
        this.adressesMail = adressesMail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLieu() {
        return lieu;
    }

    public String getHour() {
        return hour;
    }

    public String getDateAnnee() {
        return dateAnnee;
    }

    public String getDateMois() {
        return dateMois;
    }

    public String getDateJour() {
        return dateJour;
    }

    public  List<String> getAdressesMail() {return adressesMail;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reunion reunion = (Reunion) o;
        return Objects.equals(id, reunion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
