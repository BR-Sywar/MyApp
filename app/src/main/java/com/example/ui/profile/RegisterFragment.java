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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.MainActivity;
import com.example.myapp.R;

import java.util.regex.Pattern;

public class RegisterFragment extends Fragment {

    private EditText username , email , password ,repassword ;

    private Button register;
    private ImageView swipeup;


    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View root = inflater.inflate(R.
                layout.fragment_registre, container, false);

        username = root.findViewById(R.id.Nom);
        email= root.findViewById(R.id.et_email);
        password = root.findViewById(R.id.et_password);
        repassword = root.findViewById(R.id.remdp);

        register  = root.findViewById(R.id.btn_registre);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(String.valueOf(MainActivity.class));
                startActivity(myIntent);
            }
        });


        swipeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(String.valueOf(LoginFragment.class));
                startActivity(myIntent);
            }
        });

        return root;
    }

    private boolean validateEmail() {
        String emailInput = email.getEditableText().toString().trim();

        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = username.getEditableText().toString().trim();

        if (usernameInput.isEmpty()) {
            username.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            username.setError("Username too long");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput =password.getEditableText().toString().trim();

        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
    private boolean validaterePassword() {
        String passwordInput = repassword.getEditableText().toString().trim();

        if (passwordInput.isEmpty()) {
            repassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            repassword.setError("Password too weak");
            return false;
        } else {
            repassword.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }

        String input = "Email: " + email.getEditableText().toString();
        input += "\n";
        input += "Username: " + username.getEditableText().toString();
        input += "\n";
        input += "Password: " + password.getEditableText().toString();
        input += "Password: " + repassword.getEditableText().toString();



    }





}