package com.secpickup.android_front;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.secpickup.android_front.modele.Positions;
import com.secpickup.android_front.retrofit.PositionApi;
import com.secpickup.android_front.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationTrackingService extends Service {

    private LocationManager locationManager;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private String username=null;
    private LocationListener locationListener;

    @Override
    public void onCreate() {
        super.onCreate();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        startLocationUpdates();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Vous pouvez lancer le suivi de la localisation ici, si nécessaire.
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Arrêtez les mises à jour de localisation lorsque le service est détruit
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    private void startLocationUpdates() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    // Mettez à jour la position et affichez-la avec un message Toast
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    showToast("Latitude: " + latitude + ", Longitude: " + longitude);

                    // Insérez les données dans la base de données
                    updateDatabase(latitude, longitude,username);
                }
            }

            // ... (autres méthodes du LocationListener)
        };

        // Demander des mises à jour de localisation toutes les 10 secondes (10000 ms) ou lors d'un déplacement de 10 mètres
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Considérez l'appel de ActivityCompat#requestPermissions ici
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, locationListener);
    }

    private void updateDatabase(final double lat, final double lon,final String username) {
        // Utilisez un AsyncTask pour effectuer des opérations de base de données en arrière-plan
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    RetrofitService retrofitServiceAbs = new RetrofitService();
                    PositionApi positionApi = retrofitServiceAbs.getRetrofit().create(PositionApi.class);

                    positionApi.registerPosition(new Positions(lat, lon, username)).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(LocationTrackingService.this, "onResponse Test", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(LocationTrackingService.this, "onFailure Test " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
        Toast.makeText(LocationTrackingService.this, message, Toast.LENGTH_SHORT).show();
    }
}

