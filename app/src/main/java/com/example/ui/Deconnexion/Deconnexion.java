package com.example.ui.Deconnexion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.MainActivity;
import com.example.myapp.R;
import com.example.utils.CustomLoginDialog;
import com.example.utils.SessionHandler;


public class Deconnexion extends Fragment {
    private Button logout ;
    private SessionHandler sessionHandler ;
    private CustomLoginDialog customLoginDialog ;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View root = inflater.inflate(R.layout.fragment_deconnexion, container, false);
        logout = root.findViewById(R.id.btn_logout);
        sessionHandler = SessionHandler.getInstance(getContext());
        customLoginDialog = new CustomLoginDialog(getContext(),null);
        logoutUSer();
        return root;
    }

    private  void  logoutUSer() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sessionHandler.isLoggedIn()) {
                    sessionHandler.logoutUser();
                    Toast.makeText(getContext(), "Déconnecté avec succès", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                } else {
                    customLoginDialog.show();
                }
            }
        });
    }
}