package com.secpickup.android_front;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.secpickup.android_front.modele.Address;
import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.Trajet;


import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;

// ... (Autres imports)

public class Visualiser_Trajet extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private List<LatLng> trajet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiser_trajet);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng c1 = new LatLng(34.25852992783392, -6.598575550694214);
        LatLng c2 = new LatLng(34.25715548771127, -6.607948478840111);
        LatLng c3 = new LatLng(34.252547063324435, -6.615095782805417);
        LatLng c4 = new LatLng(34.257604804511175, -6.604166132933035);
        LatLng c5 = new LatLng(34.265304126671225, -6.617390146871434);


        trajet.add(c1);
        trajet.add(c2);
        trajet.add(c3);
        trajet.add(c4);
        trajet.add(c5);

        // Afficher les marqueurs sur la carte
        for (LatLng point : trajet) {
            googleMap.addMarker(new MarkerOptions().position(point).title("Marqueur"));
        }

        // Obtenir l'itinéraire optimal entre le premier et le dernier élément de la liste

        getDirections(trajet);
    }

    private void getDirections(List<LatLng> waypoints) {
        try {
            // Obtenir l'itinéraire optimal entre le premier et le dernier point
            LatLng from = waypoints.get(0);
            LatLng to = waypoints.get(waypoints.size() - 1);

            // Construire l'URI avec les coordonnées du premier et du dernier point
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" +
                    from.latitude + "," + from.longitude + "/" +
                    to.latitude + "," + to.longitude);

            // Créer une intention pour ouvrir l'application Google Maps
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps"); // Spécifier le package de Google Maps

            // Définir le drapeau pour créer une nouvelle tâche lors du lancement de l'intention
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Démarrer l'activité avec l'intention
            startActivity(intent);
        } catch (ActivityNotFoundException exception) {
            // Si l'application Google Maps n'est pas installée, rediriger vers le Google Play Store
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            // Définir le drapeau pour créer une nouvelle tâche lors du lancement de l'intention
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Démarrer l'activité avec l'intention
            startActivity(intent);
        }
    }

}
