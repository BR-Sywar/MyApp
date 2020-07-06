package com.example.network.response;



import java.util.List;

import com.example.data.models.HorairesDeTravail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HoraireResponse {

    @SerializedName("Horaires de travail ")
    @Expose
    private List<HorairesDeTravail> horairesDeTravail = null;

    public List<HorairesDeTravail> getHorairesDeTravail() {
        return horairesDeTravail;
    }

    public void setHorairesDeTravail(List<HorairesDeTravail> horairesDeTravail) {
        this.horairesDeTravail = horairesDeTravail;
    }

}

