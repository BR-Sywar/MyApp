package com.example.network.response;

import com.example.data.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("data")
    @Expose
    private User data;

    public LoginResponse() {
        this.error = null;
        this.data = null;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
