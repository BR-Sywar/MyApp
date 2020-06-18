package com.example.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.Api.MySingleton;
import com.example.Api.Urls;
import com.example.myapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registre  extends AppCompatActivity {


    EditText email, password, repassword, nom, prenome ,username, Telephone,ville;
    String Nom, Prenome, Username, Email ,Password , Repassword ,Numero ,Ville;
    Button registre;
    TextView login;
    AlertDialog.Builder builder;



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
                        builder.setTitle("une chose et en erreur");
                        builder.setMessage("merci de remplisser tous les champs");
                        displayAlert("input_error");

            }else{

                        if (!(Password.equals(Repassword))) {
                            builder.setTitle("une chose et en erreur");
                            builder.setMessage("Vérifier votre mot de passe");
                            displayAlert("input_error");
                        }
                        else {
                            StringRequest stringRequest=new StringRequest(Request.Method.POST,  Urls.URL_REGISTER,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONArray jsonArray =new JSONArray(response);
                                                JSONObject jsonObject= jsonArray.getJSONObject(0);
                                                String code =jsonObject.getString("code");
                                                String message =jsonObject.getString("message");
                                                builder.setTitle("server Response ...");
                                                builder.setMessage(message);
                                                displayAlert(code);



                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }

                            }){
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String,String> params =new HashMap<String, String>();
                                    params.put("name",Nom);
                                     params.put("Prenome",Prenome);
                                    params.put("Username",Username);
                                    params.put("email",Email);
                                    params.put("Mot de passe ",Password);
                                    params.put("Re mot de passe ",Repassword);
                                    params.put("Téléphone",Numero);
                                    params.put("Ville",Ville);
                                    return params;
                                }
                            };
                            MySingleton.getInstance(Registre.this).addToRequestque(stringRequest);
                        }
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


