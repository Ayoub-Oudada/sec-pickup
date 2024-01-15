package com.secpickup.android_front;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.secpickup.android_front.modele.EleveDto;
import com.secpickup.android_front.modele.Positions;
import com.secpickup.android_front.retrofit.EleveApi;
import com.secpickup.android_front.retrofit.PositionApi;
import com.secpickup.android_front.retrofit.RetrofitService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendLocationActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private  String username=null;
    private Handler handler;
    private TextView textView;
    private LocationListener locationListener; // Déplacez la déclaration ici

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_location);
        textView = findViewById(R.id.textViewloc);

        handler = new Handler();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Vérifiez les autorisations avant de demander les mises à jour de localisation
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Demander la permission
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            // Les autorisations sont déjà accordées
            startLocationUpdates();
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
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Considérez l'appel de ActivityCompat#requestPermissions ici
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, locationListener);
    }

    private void updateDatabase(final double lat, final double lon, final String username) {
        // Utilisez un AsyncTask pour effectuer des opérations de base de données en arrière-plan
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {

                    RetrofitService retrofitServiceAbs = new RetrofitService();
                    PositionApi positionApi = retrofitServiceAbs.getRetrofit().create(PositionApi.class);

                    positionApi.registerPosition(new Positions(lat,lon,username)).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(SendLocationActivity.this, "onResponse Test", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                           Toast.makeText(SendLocationActivity.this, "onFailure Test "+t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                } catch (Exception f) {
                    f.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Arrêtez les mises à jour de localisation lorsque l'activité est détruite
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    private void showToast(final String message) {
        // Utilisez un Handler pour afficher le message Toast sur le thread principal
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SendLocationActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
