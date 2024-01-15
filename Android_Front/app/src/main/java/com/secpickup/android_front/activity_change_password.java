package com.secpickup.android_front;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.secpickup.android_front.modele.User;
import com.secpickup.android_front.modele.UserAccountType;
import com.secpickup.android_front.retrofit.RetrofitService;
import com.secpickup.android_front.retrofit.UserApi;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_change_password extends AppCompatActivity {
    private EditText oldPasswordEditText, newPasswordEditText, confirmPasswordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldPasswordEditText = findViewById(R.id.changePassword_old);
        newPasswordEditText = findViewById(R.id.changePassword_new);
        confirmPasswordEditText = findViewById(R.id.changePassword_confirm);
        Button EnregistrerButton = findViewById(R.id.changePassword_Enregitrer);
        Button AnnulerButton = findViewById(R.id.changePassword_Annuler);


        RetrofitService retrofitService =new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        EnregistrerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String oldPassword = oldPasswordEditText.getText().toString().trim();
                String newPassword = newPasswordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();


                if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(activity_change_password.this, "all The fields are required and cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!newPassword.equals(confirmPassword)) {
                    Toast.makeText(activity_change_password.this, "New password and confirm password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                Long currentUserId = 1L;
                userApi.updateUserPassword(currentUserId, oldPassword, newPassword).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(activity_change_password.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            int statusCode = response.code();
                            Log.e("Error", "Error Code: " + statusCode);
                            Toast.makeText(activity_change_password.this, "Password change failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(activity_change_password.this, "Error: Password change failed", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(activity_change_password.class.getName()).log(Level.SEVERE, "Error occurred!", t);
                    }
                });
            }
        });
    }

}
