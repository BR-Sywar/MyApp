package com.example.network.response;

import com.example.data.models.Entreprise;
import com.example.data.models.Event;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventResponse {


        @SerializedName("entreprises")
        @Expose
        private List<Event> entreprises = null;

        public List<Event> getEvents() {
            return entreprises;
        }

        public void setEvents(List<Event> entreprises) {
            this.entreprises = entreprises;
        }


}
