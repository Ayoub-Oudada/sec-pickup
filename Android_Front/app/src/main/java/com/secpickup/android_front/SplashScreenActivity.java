package com.secpickup.android_front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //redirection vers main activity apres 3 secondes
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //demarrer une page
                Intent intent= new Intent(getApplicationContext(), activity_login.class);
                startActivity(intent);
                finish();
            }
        };
        // handler post delayed
        new Handler().postAtTime(runnable, 3000);
    }
}