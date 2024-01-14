package com.secpickup.android_front.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.secpickup.android_front.R;
import com.secpickup.android_front.retrofit.RetrofitService;
import com.secpickup.android_front.retrofit.UserApi;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangerMDPFragment extends Fragment {
    private EditText oldPasswordEditText, newPasswordEditText, confirmPasswordEditText;

    public ChangerMDPFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_changer_m_d_p, container, false);

        oldPasswordEditText = view.findViewById(R.id.changePassword_old);
        newPasswordEditText = view.findViewById(R.id.changePassword_new);
        confirmPasswordEditText = view.findViewById(R.id.changePassword_confirm);
        Button enregistrerButton = view.findViewById(R.id.changePassword_Enregitrer);
        Button annulerButton = view.findViewById(R.id.changePassword_Annuler);

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        enregistrerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPassword = oldPasswordEditText.getText().toString().trim();
                String newPassword = newPasswordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();

                if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(getActivity(), "All the fields are required and cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!newPassword.equals(confirmPassword)) {
                    Toast.makeText(getActivity(), "New password and confirm password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                Long currentUserId = 1L;
                userApi.updateUserPassword(currentUserId, oldPassword, newPassword).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Password changed successfully", Toast.LENGTH_SHORT).show();
                            // Finish the fragment or perform any desired action upon successful password change
                        } else {
                            int statusCode = response.code();
                            Log.e("Error", "Error Code: " + statusCode);
                            Toast.makeText(getActivity(), "Password change failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error: Password change failed", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(ChangerMDPFragment.class.getName()).log(Level.SEVERE, "Error occurred!", t);
                    }
                });
            }
        });

        return view;
    }
}
