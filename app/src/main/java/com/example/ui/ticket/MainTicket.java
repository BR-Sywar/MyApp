package com.example.ui.ticket;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;

public class MainTicket extends AppCompatActivity {
    private AutoCompleteTextView tict;
    private Button valider;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        tict = (AutoCompleteTextView) findViewById(R.id.tict);
        valider = (Button) findViewById(R.id.valider);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tict.getText().toString().trim().isEmpty()) {
                    Toast.makeText(v.getContext(), "please fill the feild", Toast.LENGTH_LONG).show();
                                                                }
                else {

                    int tkid = Integer.parseInt(tict.getText().toString().trim());


                    Intent ticket_deta = new Intent(v.getContext(), MainTicketReserver.class);
                    ticket_deta.putExtra("TicDeatils", ticket_deta);
                    startActivity(ticket_deta);
                    finish();
                }
                Toast.makeText(MainTicket.this, "Sorry :) Not Found", Toast.LENGTH_SHORT).show();


                                            }
                });
    }
}




