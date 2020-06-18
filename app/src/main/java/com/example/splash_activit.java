package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.myapp.R;
import com.example.utils.SessionHandler;

public class splash_activit extends Activity {

    SessionHandler sessionHandler ;
    // SplashScreen Splash;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
            sessionHandler = SessionHandler.getInstance(getApplicationContext());
        /** Creates a count down timer, which will be expired after 5000 milliseconds */
        new CountDownTimer(3000, 1000) {


            /**
             * This method will be invoked on finishing or expiring the timer
             */
            @Override
            public void onFinish() {
                Intent intent ;
                /** Creates an intent to start new activity */
                if (sessionHandler.isLoggedIn()) {
                    intent = new Intent(splash_activit.this, MainActivity.class);
                }else intent = new Intent(splash_activit.this, welcome.class);


                startActivity(intent);

                finish();
            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();

    }
}