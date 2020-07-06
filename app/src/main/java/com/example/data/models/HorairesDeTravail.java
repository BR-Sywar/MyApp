package com.example.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.Inet4Address;

public class HorairesDeTravail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jours")
    @Expose
    private String jours;

    @SerializedName("heure_debut_matin")
    @Expose
    private String heureDebutMatin;
    @SerializedName("heure_fin_matin")
    @Expose
    private String heureFinMatin;
    @SerializedName("heure_debut_ap")
    @Expose
    private String heureDebutAp;
    @SerializedName("heure_fin_ap")
    @Expose
    private String heureFinAp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJours() {
        return jours;
    }

    public void setJours(String jours) {
        this.jours = jours;
    }
/*
    public String getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(String entrepriseId) {
        this.entrepriseId = entrepriseId;
    }*/

    public String getHeureDebutMatin() {
        return heureDebutMatin;
    }

    public void setHeureDebutMatin(String heureDebutMatin) {
        this.heureDebutMatin = heureDebutMatin;
    }

    public String getHeureFinMatin() {
        return heureFinMatin;
    }

    public void setHeureFinMatin(String heureFinMatin) {
        this.heureFinMatin = heureFinMatin;
    }

    public String getHeureDebutAp() {
        return heureDebutAp;
    }

    public void setHeureDebutAp(String heureDebutAp) {
        this.heureDebutAp = heureDebutAp;
    }

    public String getHeureFinAp() {
        return heureFinAp;
    }

    public void setHeureFinAp(String heureFinAp) {
        this.heureFinAp = heureFinAp;
    }

}
