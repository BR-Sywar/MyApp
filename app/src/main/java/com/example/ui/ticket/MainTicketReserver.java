package com.example.ui.ticket;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;


public class MainTicketReserver extends AppCompatActivity {

    private EditText id, date, heure, service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);


        id =  findViewById(R.id.id);
        date =  findViewById(R.id.date);
        heure = findViewById(R.id.heure);
        

    }

    public EditText getId() {
        return id;
    }

    public void setId(EditText id) {
        this.id = id;
    }

    public EditText getDate() {
        return date;
    }

    public void setDate(EditText date) {
        this.date = date;
    }

    public EditText getHeure() {
        return heure;
    }

    public void setHeure(EditText heure) {
        this.heure = heure;
    }

    public EditText getService() {
        return service;
    }

    public void setService(EditText service) {
        this.service = service;
    }
}