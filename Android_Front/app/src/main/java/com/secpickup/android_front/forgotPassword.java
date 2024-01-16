package com.secpickup.android_front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.secpickup.android_front.retrofit.RetrofitService;
import com.secpickup.android_front.retrofit.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.logging.Level;
import java.util.logging.Logger;



public class forgotPassword extends AppCompatActivity {

    private EditText usernameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        usernameEditText = findViewById(R.id.editTextUsername);
        Button EnvoyerButton = findViewById(R.id.buttonForgotPassword);


        RetrofitService retrofitService =new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        EnvoyerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = usernameEditText.getText().toString().trim();

                if (username.isEmpty()) {
                    Toast.makeText(forgotPassword.this, "This field is required and cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                userApi.forgotPassword(username).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(forgotPassword.this, "Password sent to your email address", Toast.LENGTH_SHORT).show();
                            // Use a Handler to delay the navigation
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent loginIntent = new Intent(forgotPassword.this, activity_login.class);
                                    startActivity(loginIntent);
                                    finish();
                                }
                            }, 2000);
                        }else {
                            int statusCode = response.code();
                            Log.e("Error", "Error Code: " + statusCode);
                            Toast.makeText(forgotPassword.this, "Failed to initiate password retrieval", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(forgotPassword.this, "Network error. Please try again.", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(forgotPassword.class.getName()).log(Level.SEVERE, "Error occurred!", t);

                    }
                });
            }
        });
    }

}

