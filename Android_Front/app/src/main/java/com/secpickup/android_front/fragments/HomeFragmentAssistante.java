package com.secpickup.android_front.fragments;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.secpickup.android_front.Adapter.Assistantepageradapter;
import com.secpickup.android_front.LoadStudent;
import com.secpickup.android_front.assistantebusmain;
import com.secpickup.android_front.modele.Eleve;
import java.util.ArrayList;
import java.util.List;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.secpickup.android_front.R;
import com.secpickup.android_front.modele.Positions;
import com.secpickup.android_front.retrofit.PositionApi;
import com.secpickup.android_front.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Intent;
import androidx.annotation.Nullable;


public class HomeFragmentAssistante extends Fragment implements LoadStudent.LoadStudentCallback {

    private List<Eleve> childrenList;
    private Assistantepageradapter pagerAdapter;
    private String username;
    private String type;
    private LocationManager locationManager;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private Handler handler;
    private LocationListener locationListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        childrenList = new ArrayList<>();
        Intent intent = requireActivity().getIntent();
        if (intent.hasExtra("username") && intent.hasExtra("type")) {
            username = intent.getStringExtra("username");
            type = intent.getStringExtra("type");
        }
        LoadStudent loadStudent = new LoadStudent();
        loadStudent.loadEleves(username, type, this);
        pagerAdapter = new Assistantepageradapter(requireContext(), childrenList);
        pagerAdapter.setOnButtonClickListener(new Assistantepageradapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(int position, String situation) {
                // Faites quelque chose lorsqu'un bouton est cliqué dans l'adaptateur
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_assistante, container, false);
        FloatingActionButton startServiceButton = view.findViewById(R.id.btnStartService);
        ViewPager viewPager = view.findViewById(R.id.eleveList_recyclerView);
        viewPager.setAdapter(pagerAdapter);

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancez le service en cliquant sur le bouton
                startLocationUpdates();
                Toast.makeText(requireContext(), "Vous avez cliqué sur Tracking", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onStudentListLoaded(List<Eleve> eleveList) {
        childrenList.clear();
        for (Eleve eleve : eleveList) {
            if (eleve != null) {
                childrenList.add(eleve);
            }
        }
        pagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailedToLoadStudents() {
        // Gérer l'échec du chargement des étudiants si nécessaire
    }

    private void startLocationUpdates() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    showToast("Latitude: " + latitude + ", Longitude: " + longitude);
                    updateDatabase(latitude, longitude, username);
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, locationListener);
    }

    private void updateDatabase(final double lat, final double lon, final String username) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    RetrofitService retrofitServiceAbs = new RetrofitService();
                    PositionApi positionApi = retrofitServiceAbs.getRetrofit().create(PositionApi.class);

                    positionApi.registerPosition(new Positions(lat, lon, username)).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(requireContext(), "onResponse Test", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(requireContext(), "onFailure Test " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (Exception f) {
                    f.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    private void showToast(final String message) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
