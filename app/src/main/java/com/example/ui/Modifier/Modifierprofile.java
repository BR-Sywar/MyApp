package com.example.ui.Modifier;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.data.models.User;
import com.example.myapp.R;
import com.example.utils.SessionHandler;

public class Modifierprofile extends Fragment {
    private

    TextView username, email, Nom, prenom, mdp, Tel, ville, remdp;
    ImageView pdp, camera;
    Button btn_registre;
    private SessionHandler sessionHandler ;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root;
        root = inflater.inflate(R.layout.activity_modifier__profile, container, false);


        btn_registre = root.findViewById(R.id.btn_registre);
        username = root.findViewById(R.id.username);
        email = root.findViewById(R.id.email);
        Nom = root.findViewById(R.id.Nom);
        prenom = root.findViewById(R.id.prenom);
        mdp = root.findViewById(R.id.mdp);
        Tel = root.findViewById(R.id.Tel);
        ville = root.findViewById(R.id.ville);
        remdp = root.findViewById(R.id.remdp);
        pdp = root.findViewById(R.id.pdp);
        camera = root.findViewById(R.id.camera);
        sessionHandler = SessionHandler.getInstance(getContext());
        setProfileDetails();


        return root;
    }
    private void setProfileDetails() {
        if (sessionHandler.isLoggedIn()) {
            User user= sessionHandler.getUserDetails();
            username.setText(user.getUsername());
            email.setText(user.getEmail());
            Nom.setText(user.getNom());
            prenom.setText(user.getPrenom());
            Tel.setText(user.getTel());
            ville.setText(user.getVille());

        }

    }
}

