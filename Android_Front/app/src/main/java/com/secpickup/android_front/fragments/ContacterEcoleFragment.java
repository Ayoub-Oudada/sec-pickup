package com.secpickup.android_front.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.secpickup.android_front.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.secpickup.android_front.modele.Ecole;
import com.secpickup.android_front.retrofit.EcoleApi;
import com.secpickup.android_front.retrofit.RetrofitService;

public class ContacterEcoleFragment extends Fragment {

    private TextView nomTextView;
    private TextView numeroTelephoneTextView;
    private TextView emailTextView;
    private TextView siteWebTextView;

    public ContacterEcoleFragment() {
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacter_ecole, container, false);

        nomTextView = view.findViewById(R.id.nom_ecole);
        numeroTelephoneTextView = view.findViewById(R.id.num_tele_ecole);
        emailTextView = view.findViewById(R.id.mail_eole);
        siteWebTextView = view.findViewById(R.id.site_web_ecole);
        loadEcoleData();
        return view;
    }

    private void loadEcoleData() {
        RetrofitService retrofitService = new RetrofitService();
        EcoleApi ecoleApi = retrofitService.getRetrofit().create(EcoleApi.class);
        Call<Ecole> call = ecoleApi.getEcoleById(1L);

        call.enqueue(new Callback<Ecole>() {
            @Override
            public void onResponse(Call<Ecole> call, Response<Ecole> response) {
                if (response.isSuccessful()) {
                    Ecole ecole = response.body();
                    nomTextView.setText(ecole.getNom());
                    numeroTelephoneTextView.setText(ecole.getNumeroTelephone());
                    emailTextView.setText(ecole.getEmail());
                    siteWebTextView.setText(ecole.getSiteWeb());
                } else {
                    handleError("Erreur lors de la récupération des données de l'école");
                }
            }

            @Override
            public void onFailure(Call<Ecole> call, Throwable t) {
                handleError("Erreur lors de la récupération des données de l'école");
            }
        });
    }
    private void handleError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
        Log.e("ContacterEcoleFragment", errorMessage);
    }


    public static ContacterEcoleFragment newInstance(String param1, String param2) {
        ContacterEcoleFragment fragment = new ContacterEcoleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

}