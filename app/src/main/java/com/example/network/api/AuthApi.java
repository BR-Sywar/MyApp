package com.example.network.api;

import com.example.data.models.User;
import com.example.network.response.LoginResponse;
import com.example.network.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthApi {
    @POST("login")
    Call<LoginResponse> loginUser(@Query("username") String username ,@Query("password") String password) ;
    Call<RegisterResponse> registerUser(@Body User user) ;
}
