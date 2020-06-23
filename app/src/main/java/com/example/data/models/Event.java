package com.example.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("titre")
    @Expose
    private String titre;
    @SerializedName("dateDebut")
    @Expose
    private MyDate dateDebut;
    @SerializedName("dateFin")
    @Expose
    private MyDate dateFin;
    @SerializedName("heureDebut")
    @Expose
    private String heureDebut;
    @SerializedName("heureFin")
    @Expose
    private String heureFin;
    @SerializedName("dateDebutTemp")
    @Expose
    private Object dateDebutTemp;
    @SerializedName("dateFinTemp")
    @Expose
    private Object dateFinTemp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public MyDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(MyDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public MyDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(MyDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public Object getDateDebutTemp() {
        return dateDebutTemp;
    }

    public void setDateDebutTemp(Object dateDebutTemp) {
        this.dateDebutTemp = dateDebutTemp;
    }

    public Object getDateFinTemp() {
        return dateFinTemp;
    }

    public void setDateFinTemp(Object dateFinTemp) {
        this.dateFinTemp = dateFinTemp;
    }

}



