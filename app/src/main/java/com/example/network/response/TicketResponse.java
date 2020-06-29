package com.example.network.response;

import com.example.data.models.Ticket;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TicketResponse {
    @SerializedName("data")
    @Expose
    private List<Ticket> data = null;

    public List<Ticket> getData() {
        return data;
    }

    public void setData(List<Ticket> data) {
        this.data = data;
    }
    
}
