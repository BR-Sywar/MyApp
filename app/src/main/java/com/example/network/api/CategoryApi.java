package com.example.network.api;

import com.example.network.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryApi {
    @GET("categories")
    Call<CategoryResponse> getCategories();
}
