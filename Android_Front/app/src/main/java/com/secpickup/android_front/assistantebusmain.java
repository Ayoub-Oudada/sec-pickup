package com.secpickup.android_front;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.secpickup.android_front.Adapter.Assistantepageradapter;
import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.Positions;
import com.secpickup.android_front.retrofit.PositionApi;
import com.secpickup.android_front.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class assistantebusmain extends AppCompatActivity implements LoadStudent.LoadStudentCallback {

    private List<Eleve> childrenList;
    private Assistantepageradapter pagerAdapter;
    String username;
    String type;
    private LocationManager locationManager;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private Handler handler;
    private TextView textView;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assistantebus_mainactivity);
        FloatingActionButton startServiceButton = findViewById(R.id.btnStartService);
        handler = new Handler();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        childrenList = new ArrayList<>();
        Intent intent = getIntent();
        if (intent.hasExtra("username") && intent.hasExtra("type")) {
            username = intent.getStringExtra("username");
            type = intent.getStringExtra("type");
        }
        LoadStudent loadStudent = new LoadStudent();
        loadStudent.loadEleves(username,type,this);
        pagerAdapter = new Assistantepageradapter(this, childrenList);
        pagerAdapter.setOnButtonClickListener(new Assistantepageradapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(int position, String situation) {
            }
        });
        ViewPager viewPager = findViewById(R.id.eleveList_recyclerView);
        viewPager.setAdapter(pagerAdapter);
        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancez le service en cliquant sur le bouton
                startLocationUpdates();
                Toast.makeText(assistantebusmain.this, "Vous avez cliqué sur Tracking", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onStudentListLoaded(List<Eleve> eleveList) {
        childrenList.clear();
        for (Eleve eleve : eleveList) {
            if (eleve != null) {
                childrenList.add(eleve);
            }
        }        pagerAdapter.notifyDataSetChanged();
    }
    @Override
    public void onFailedToLoadStudents() {
    }
    private void startLocationUpdates() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    showToast("Latitude: " + latitude + ", Longitude: " + longitude);
                    updateDatabase(latitude, longitude,username);
                }
            }
        };
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
    }
    private void updateDatabase(final double lat, final double lon,final String username) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {

                    RetrofitService retrofitServiceAbs = new RetrofitService();
                    PositionApi positionApi = retrofitServiceAbs.getRetrofit().create(PositionApi.class);

                    positionApi.registerPosition(new Positions(lat,lon,username)).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(assistantebusmain.this, "onResponse Test", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(assistantebusmain.this, "onFailure Test "+t.getMessage(), Toast.LENGTH_SHORT).show();

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
                Toast.makeText(assistantebusmain.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
