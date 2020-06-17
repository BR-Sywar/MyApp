package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.myapp.R;

public class splash_activit extends Activity {


    // SplashScreen Splash;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /** Creates a count down timer, which will be expired after 5000 milliseconds */
        new CountDownTimer(5000, 1000) {


            /**
             * This method will be invoked on finishing or expiring the timer
             */
            @Override
            public void onFinish() {
                /** Creates an intent to start new activity */
                Intent intent = new Intent(getBaseContext(), welcome.class);

                startActivity(intent);

                finish();
            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();

    }
}