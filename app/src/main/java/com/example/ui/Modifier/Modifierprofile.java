package com.example.ui.Modifier;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapp.R;

public class Modifierprofile extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root;
        root = inflater.inflate(R.layout.activity_modifier__profile,container,false );


       Button btn_registre = root.findViewById(R.id.btn_registre);
       TextView username = root.findViewById (R.id.username);
       TextView email = root.findViewById (R.id.email);
        TextView Nom = root.findViewById (R.id.Nom);
       TextView prenom= root.findViewById (R.id.prenom);
       TextView mdp= root.findViewById (R.id.mdp);
       TextView Tel= root.findViewById (R.id.Tel);
        TextView ville= root.findViewById (R.id.ville);
      TextView  remdp= root.findViewById (R.id.remdp);
     ImageView   pdp= root.findViewById (R.id.pdp);
      ImageView  camera= root.findViewById (R.id.camera);


        return root;
    }
}

