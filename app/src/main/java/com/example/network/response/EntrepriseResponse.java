package com.example.network.response;

import com.example.data.models.Entreprise;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EntrepriseResponse {
    @SerializedName("entreprises")
    @Expose
    private List<Entreprise> entreprises = null;

    public List<Entreprise> getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(List<Entreprise> entreprises) {
        this.entreprises = entreprises;
    }
}
