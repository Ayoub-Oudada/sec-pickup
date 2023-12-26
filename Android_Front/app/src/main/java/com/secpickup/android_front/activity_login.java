package com.secpickup.android_front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.secpickup.android_front.modele.User;
import com.secpickup.android_front.modele.UserAccountType;
import com.secpickup.android_front.retrofit.RetrofitService;
import com.secpickup.android_front.retrofit.UserApi;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_login extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Spinner spinnerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editTextUsername = findViewById(R.id.login_username);
        editTextPassword = findViewById(R.id.login_password);
        spinnerOptions = findViewById(R.id.spinner);
        Button postDataButton = findViewById(R.id.login_button);

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

          postDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String selectedOption = spinnerOptions.getSelectedItem().toString().trim();


                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(activity_login.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                    return;
                }


                User user = new User(username, password, UserAccountType.valueOf(selectedOption));


                userApi.findUser(user).enqueue(new Callback<User>() {


                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User user = response.body();
                            if (user != null) {

                                String username = user.getUsername();
                                String type = String.valueOf(user.getType());


                                Toast.makeText(activity_login.this, "Authentication successful", Toast.LENGTH_SHORT).show();
                                //demarrer une page
                                if(type=="PARENT"){
                                Intent intent= new Intent(getApplicationContext(), EleveList_Activity.class);
                                intent.putExtra("username", username);
                                intent.putExtra("type", type);
                                startActivity(intent);
                                finish();
                                }
                                if(type=="ASSISTANTE"){
                                    Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                                    intent.putExtra("username", username);
                                    intent.putExtra("type", type);
                                    startActivity(intent);
                                    finish();
                                }

                            } else {

                                Toast.makeText(activity_login.this, "Empty response body", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                           int statusCode = response.code();
                            Log.e("Error", "Error Code: " + statusCode);

                            Toast.makeText(activity_login.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }



                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(activity_login.this, "Error: Athentification failed", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(activity_login.class.getName()).log(Level.SEVERE, "Error occurred!", t);
                    }
                });
            }
        });
    }

    }
