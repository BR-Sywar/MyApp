package com.example.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.Api.MySingleton;
import com.example.Api.Urls;
import com.example.MainActivity;
import com.example.data.models.User;
import com.example.myapp.R;
import com.example.network.RetrofitInstance;
import com.example.network.api.AuthApi;
import com.example.network.response.RegisterResponse;
import com.example.utils.SessionHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import kotlin.io.TextStreamsKt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registre  extends AppCompatActivity {


    EditText email, password, repassword, nom, prenome ,username, Telephone,ville;
    String Nom, Prenome, Username, Email ,Password , Repassword ,Numero ,Ville;
    Button registre;
    TextView login;
    AlertDialog.Builder builder;
    SessionHandler sessionHandler ;
    AuthApi api ;
    ProgressBar mProgressBar ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registre);
        builder = new AlertDialog.Builder(Registre.this);
        nom = findViewById(R.id.Nom);
        prenome = findViewById(R.id.prenom);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.mdp);
        repassword = findViewById(R.id.remdp);
        Telephone= findViewById(R.id.Tel);
        ville = findViewById(R.id.ville);
        registre = findViewById(R.id.btn_registre);
        login = findViewById(R.id.swipeLeft);
        mProgressBar = findViewById(R.id.register_progressBar);
        sessionHandler = SessionHandler.getInstance(getApplicationContext()) ;
        api = RetrofitInstance.getInstance().create(AuthApi.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registre.this, Login.class);
                startActivity(intent);
            }
        });

            registre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Nom = nom.getText().toString();
                    Prenome = prenome.getText().toString();
                    Username = username.getText().toString();
                    Email = email.getText().toString();
                    Password= password.getText().toString();
                    Repassword = repassword.getText().toString();
                    Numero= Telephone.getText().toString();
                    Ville = ville.getText().toString();


                    if(TextUtils.isEmpty(Nom) ||  TextUtils.isEmpty(Prenome)|| TextUtils.isEmpty(Username) ||TextUtils.isEmpty(Email)  ||TextUtils.isEmpty(Password) ||TextUtils.isEmpty(Repassword) ||TextUtils.isEmpty(Numero) ||TextUtils.isEmpty(Ville)) {
                        builder.setTitle("une chose est en erreur");
                        builder.setMessage("merci de remplisser tous les champs");
                        displayAlert("input_error");

            }else if (!(Password.equals(Repassword))) {
                        builder.setTitle("une chose est en erreur");
                        builder.setMessage("Vérifier votre mot de passe");
                        displayAlert("input_error");
                    } else {

                        mProgressBar.setVisibility(View.VISIBLE);
                        final User user = new User(Username,Nom,Prenome,Ville,"adresse"
                                ,Email,Numero,0,0,"");
                        user.setPassword(Password);
                        user.setLat(10);
                        user.setLng(11);

                        Call<RegisterResponse> call = api.registerUser(user);
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
                                    displayAlert(error);
                                } else {
                                    Toast.makeText(getApplicationContext(),"compte créé avec succès",Toast.LENGTH_SHORT).show();
                                    sessionHandler.loginUser(user);
                                    Intent intent = new Intent(Registre.this, MainActivity.class) ;
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NO_HISTORY) ;
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                                Toast.makeText(Registre.this, "error", Toast.LENGTH_LONG).show();
                                mProgressBar.setVisibility(View.GONE);
                            }
                        });


                    }
                }


            });
        }
        public void displayAlert(final String code) {
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (code.equals("input_error")) {
                   password.setText("");
                    repassword.setText("");
                }
                else if(code.equals("reg_success"))
                {
                    finish();
                }
                else  if(code.equals("reg_failed"))
                {
                    nom.setText("");
                    email.setText("");
                    password.setText("");
                    repassword.setText("");
                }

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}


