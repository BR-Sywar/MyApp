package com.example.network.api;

import com.example.network.response.RegisterResponse;
import com.example.network.response.TicketResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TicketApi {
    @GET("user-ticket/{id}")
    Call<TicketResponse> getUserTickets(@Path("id") int id);

    @POST("reserver-ticket")
    Call<RegisterResponse> reserverTicket(@Query("user_id") int user_id, @Query("entreprise_id") int entreprise_id
            , @Query("date") String date, @Query("heure") String heure);
}
