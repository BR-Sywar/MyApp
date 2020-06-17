package com.example.ui.Accueil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapp.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class HomeFragment extends Fragment {

    public View onCreateView (@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_homedashbord, container, false);
        Button btn_sante = (Button) root.findViewById(R.id.btn_sante);
        Button btn_bank = (Button) root.findViewById(R.id.btn_bank);
        Button btn_public = (Button) root.findViewById(R.id.btn_public);
        Button btn_poste = (Button) root.findViewById(R.id.btn_poste);

        btn_sante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.nav_sante);
            }
        });
        btn_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.nav_bank);
            }
        });
        btn_poste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.nav_post);
            }
        });
        btn_public.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.nav_public);
            }
        });
        return root;
    }
}
