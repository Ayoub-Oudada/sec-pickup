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

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(getApplicationContext(), activity_login.class);
                startActivity(intent);
                finish();
            }
        };
        new Handler().postAtTime(runnable, 3000);
    }
}