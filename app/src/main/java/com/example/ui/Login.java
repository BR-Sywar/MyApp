package com.example.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.MainActivity;
import com.example.data.models.Entreprise;
import com.example.myapp.R;
import com.example.network.RetrofitInstance;
import com.example.network.api.AuthApi;
import com.example.network.response.LoginResponse;
import com.example.utils.SessionHandler;

import org.json.JSONException;
import org.json.JSONObject;

import kotlin.io.TextStreamsKt;
import retrofit2.Call;
import retrofit2.Callback;

public class Login extends AppCompatActivity {

    EditText mail, password;
    String Email, Password;
    Button login;
    ImageView Registre;
    AuthApi api ;
    AlertDialog.Builder builder;
    SessionHandler sessionHandler ;
    ProgressBar mProgressBar ;
    private Entreprise entreprise ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_login);
        builder = new AlertDialog.Builder(Login.this);
        mail = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        Registre = findViewById(R.id.swipeup_login);
        login = findViewById(R.id.btn_login);
        mProgressBar = findViewById(R.id.login_progressBar);
        sessionHandler = SessionHandler.getInstance(getApplicationContext());
        entreprise = getIntent().getParcelableExtra("entre") ;
        api = RetrofitInstance.getInstance().create(AuthApi.class);
        Registre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), com.example.ui.Registre.class);
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
                        mProgressBar.setVisibility(View.VISIBLE);
                    Call<LoginResponse> call = api.loginUser(Email , Password) ;
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                            LoginResponse loginResponse = response.body() ;
                            mProgressBar.setVisibility(View.GONE);
                            if ( response.code() != 200)
                             {  String error = "" ;
                                 try {
                                     JSONObject jsonObject = new JSONObject( TextStreamsKt.readText(response.errorBody().charStream())) ;
                                     error = jsonObject.getString("error");
                                 } catch (JSONException e) {
                                     e.printStackTrace();
                                 }
                                 displayAlert( error);
                             } else if (loginResponse.getData() != null) {

                              sessionHandler.loginUser(loginResponse.getData());
                                Intent intent = new Intent(Login.this, MainActivity.class) ;
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NO_HISTORY) ;
                                if (entreprise != null) {
                                    Bundle   bundle = new Bundle() ;
                                    bundle.putParcelable("entre",entreprise);
                                    intent.putExtras(bundle);
                                }

                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(Login.this, "error", Toast.LENGTH_LONG).show();
                            mProgressBar.setVisibility(View.GONE);
                        }
                    });

                    /*
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
                    */

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
