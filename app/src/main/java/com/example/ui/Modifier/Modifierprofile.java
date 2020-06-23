package com.example.ui.Modifier;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.data.models.User;
import com.example.myapp.R;
import com.example.network.RetrofitInstance;
import com.example.network.api.AuthApi;
import com.example.network.response.RegisterResponse;
import com.example.utils.CustomLoginDialog;
import com.example.utils.SessionHandler;

import org.json.JSONException;
import org.json.JSONObject;

import kotlin.io.TextStreamsKt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Modifierprofile extends Fragment {
    private

    TextView username, email, nom, prenom, mdp, Telephone, ville, remdp;
    String Nom, Prenome, Username, Email ,Password , Repassword ,Numero ,Ville;
    ImageView pdp, camera;
    Button btn_registre;
    private SessionHandler sessionHandler ;
    AuthApi api ;
    ProgressBar mProgressBar ;
    User userCache ;
    User user ;
    CustomLoginDialog customLoginDialog ;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root;
        root = inflater.inflate(R.layout.activity_modifier__profile, container, false);


        btn_registre = root.findViewById(R.id.btn_registre);
        username = root.findViewById(R.id.username);
        email = root.findViewById(R.id.email);
        nom = root.findViewById(R.id.Nom);
        prenom = root.findViewById(R.id.prenom);
        mdp = root.findViewById(R.id.mdp);
        Telephone = root.findViewById(R.id.Tel);
        ville = root.findViewById(R.id.ville);
        remdp = root.findViewById(R.id.remdp);
        pdp = root.findViewById(R.id.pdp);
        camera = root.findViewById(R.id.camera);
        sessionHandler = SessionHandler.getInstance(getContext());
        customLoginDialog = new CustomLoginDialog(getContext()) ;
        mProgressBar = root.findViewById(R.id.update_progressBar);
        api = RetrofitInstance.getInstance().create(AuthApi.class);
        setProfileDetails();
        updateProfile();


        return root;
    }
    private void setProfileDetails() {
        if (sessionHandler.isLoggedIn()) {
            userCache= sessionHandler.getUserDetails();
            username.setText(userCache.getUsername());
            email.setText(userCache.getEmail());
            nom.setText(userCache.getNom());
            prenom.setText(userCache.getPrenom());
            Telephone.setText(userCache.getTel());
            ville.setText(userCache.getVille());

        }else customLoginDialog.show();

    }
    private void  updateProfile () {

        btn_registre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nom = nom.getText().toString();
                Prenome = prenom.getText().toString();
                Username = username.getText().toString();
                Email = email.getText().toString();
                Password= mdp.getText().toString();
                Repassword = remdp.getText().toString();
                Numero= Telephone.getText().toString();
                Ville = ville.getText().toString();
                mProgressBar.setVisibility(View.VISIBLE);
                 user = new User(Username,Nom,Prenome,Ville,userCache.getAdresse()
                        ,Email,Numero,userCache.getCodePostal(),userCache.getId(),userCache.getApiKey());
                user.setPassword(Password);

                Call<RegisterResponse> call = api.updateUser(user.getId(),user);
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        mProgressBar.setVisibility(View.GONE);
                        if (response.code() != 200) {
                            String error = "";
                            try {
                                JSONObject jsonObject = new JSONObject(TextStreamsKt.readText(response.errorBody().charStream()));
                                error = jsonObject.getString("error");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        } else {
                            Toast.makeText(getContext(),"modifié avec succès",Toast.LENGTH_SHORT).show();
                            sessionHandler.logoutUser();
                            sessionHandler.loginUser(user);
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}

