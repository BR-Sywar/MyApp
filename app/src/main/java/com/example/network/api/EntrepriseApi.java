package com.example.network.api;

import com.example.network.response.EntrepriseResponse;
import com.example.network.response.EventResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EntrepriseApi {

    @GET("categorie-entreprise/{id}")
    Call<EntrepriseResponse> getEntreprises(@Path("id") int id);
    @GET("entreprise-event/{id}")
    Call<EventResponse> getEvents(@Path("id") int id);
}
