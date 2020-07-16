package com.example.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.data.models.User;
import com.example.myapp.R;
import com.example.utils.CustomLoginDialog;
import com.example.utils.SessionHandler;

public class ProfileFragment extends Fragment {

    private TextView et_email , et_nom,et_tel,et_local;
    private Button btn_modif;
    private SessionHandler sessionHandler ;
    CustomLoginDialog customLoginDialog ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_profile, container, false);

        sessionHandler =SessionHandler.getInstance(getContext());
        customLoginDialog = new CustomLoginDialog(getContext(),null);
        btn_modif = root.findViewById(R.id.btn_modif);
        btn_modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.nav_parametre);
            }
        });

        et_nom = root.findViewById(R.id.et_nom);
       et_email = root.findViewById(R.id.et_email);
        et_tel = root.findViewById(R.id.et_tel);
        et_local = root.findViewById(R.id.et_local);



        setProfileDetails();

        return root;
    }
    private void  setProfileDetails() {
        if (sessionHandler.isLoggedIn()) {
            User user = sessionHandler.getUserDetails();
            et_nom.setText(user.getNom());
            et_email.setText(user.getEmail());
            et_tel.setText(user.getTel());
            et_local.setText(user.getAdresse());
        } else {
            customLoginDialog.show();
        }

    }
}

