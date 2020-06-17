package com.example.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapp.R;
import com.example.ui.MesReservation.ReservationFragment;
import com.example.ui.Modifier.Modifierprofile;

public class ProfileFragment extends Fragment {

    private TextView et_email,et_tel,et_local,et_ticket;
    private Button btn_modif;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_profile, container, false);


        btn_modif = root.findViewById(R.id.btn_modif);
        btn_modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent (String.valueOf(Modifierprofile.class));
                startActivity(myIntent);
            }
        });


       et_email = root.findViewById(R.id.et_email);
        et_tel = root.findViewById(R.id.et_tel);
        et_local = root.findViewById(R.id.et_local);
        et_ticket = root.findViewById(R.id.et_ticket);
        et_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(String.valueOf(ReservationFragment.class));
                startActivity(myIntent);
            }
        });




        return root;
    }
}

