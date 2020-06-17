package com.example.Api;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText mail, password;
    String Email, Password;
    Button login;
    ImageView Registre;

    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_login);
        builder = new AlertDialog.Builder(Login.this);
        mail = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        Registre = findViewById(R.id.swipeup_login);
        login = findViewById(R.id.btn_login);

        Registre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), Registre.class);
                startActivity(myIntent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = mail.getText().toString();
                Password = password.getText().toString();
                if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password)) {
                    builder.setTitle("une chose et en erreur");
                    displayAlert("Enter a validate email and password ");
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,  Urls.URL_LOGIN,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String code = ((JSONObject) jsonObject).getString("code");
                                        if (code.equals("login_failed")) {
                                            builder.setTitle("login error ...");
                                            displayAlert(jsonObject.getString("message"));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, "error", Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("email", Email);
                            params.put("password", Password);
                            return params;
                        }
                    };
                    MySingleton.getInstance(Login.this).addToRequestque(stringRequest);
        }
    }

});


        }



public void displayAlert(String message) {
        builder.setMessage(message);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialog, int which) {
        mail.setText("");
        password.setText("");
        }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        }
        }
