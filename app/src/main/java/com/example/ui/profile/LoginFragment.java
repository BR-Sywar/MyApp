package com.example.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.MainActivity;
import com.example.myapp.R;

import java.util.regex.Pattern;


public class LoginFragment extends Fragment {


    private EditText et_username;
    private EditText et_email;
    private EditText et_password;
    private ImageView swipeup;
    private TextView motdpassoblie;

    //je peux le supprimer
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View root = inflater.inflate(R.layout.fragment_login, container, false);

        et_username= root.findViewById(R.id.et_email);
        et_email= root.findViewById(R.id.et_email);
        et_password= root.findViewById(R.id.et_password);


        motdpassoblie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(String.valueOf(LoginFragment.class));
                startActivity(myIntent);
            }
        });

        Button btn_login = root.findViewById(R.id.btn_modif);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(myIntent);
            }
        });


        swipeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), RegisterFragment.class);
                startActivity(myIntent);
            }
        });

        return root;
    }

    private boolean validateEmail() {
        String emailInput = et_email.getEditableText().toString().trim();

        if (emailInput.isEmpty()) {
            et_email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            et_email.setError("Please enter a valid email address");
            return false;
        } else {
            et_email.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = et_username.getEditableText().toString().trim();

        if (usernameInput.isEmpty()) {
            et_username.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            et_username.setError("Username too long");
            return false;
        } else {
            et_username.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = et_password.getEditableText().toString().trim();

        if (passwordInput.isEmpty()) {
            et_password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            et_password.setError("Password too weak");
            return false;
        } else {
            et_password.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }

        String input = "Email: " + et_email.getEditableText().toString();
        input += "\n";
        input += "Username: " + et_username.getEditableText().toString();
        input += "\n";
        input += "Password: " + et_password.getEditableText().toString();



    }

}


