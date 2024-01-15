package com.secpickup.android_front;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.secpickup.android_front.modele.Address;
import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.Positions;
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

public class Visualiser_Trajet extends FragmentActivity implements OnMapReadyCallback,LoadTrajet.LoadTrajetCallback {

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


        //trajet.add(c1);
        trajet.add(c2);
        trajet.add(c3);
        trajet.add(c4);
        //trajet.add(c5);


        String assistante = null;
        Intent intent = getIntent();
        if (intent.hasExtra("Assistante")) {
            assistante = intent.getStringExtra("Assistante");
        }

        LoadTrajet loadTrajet =  new LoadTrajet();
        loadTrajet.loadTrajet("Alpha",this);




        // Créer une zone englobante pour tous les marqueurs
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(c1);
        builder.include(c5);
        for (LatLng point : trajet) {
            builder.include(point);

            BitmapDescriptor customMarker = getResizedBitmapDescriptor(R.drawable.bus, 100, 100);

            MarkerOptions markerOptions = new MarkerOptions()
                    .position(point)
                    .title("TrajetBus")
                    .icon(customMarker);

            // Ajoutez le marqueur à la carte
            googleMap.addMarker(markerOptions);
        }

        //position parent

        BitmapDescriptor customMarker = getResizedBitmapDescriptor(R.drawable.house, 100, 100);
        MarkerOptions markerOptions = new MarkerOptions()
                .title("Home")
                .position(c5)
                .icon(customMarker);

        // Ajoutez le marqueur à la carte
        googleMap.addMarker(markerOptions);


        //position ecole


        BitmapDescriptor customMarker2 = getResizedBitmapDescriptor(R.drawable.ecole, 100, 100);

        MarkerOptions markerOptions2 = new MarkerOptions()
                .position(c1)
                .title("School")
                .icon(customMarker2);

        // Ajoutez le marqueur à la carte
        googleMap.addMarker(markerOptions2);
        // Zoomer sur la zone englobante avec un padding personnalisé (ici 100 pixels)
        LatLngBounds bounds = builder.build();
        int padding = 100;
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.moveCamera(cameraUpdate);



        // Obtenir l'itinéraire optimal entre le premier et le dernier élément de la liste

        trajet.add(trajet.size(),c5);
        trajet.add(0,c1);
        Toast.makeText(this, trajet.size()+"", Toast.LENGTH_SHORT).show();
        //getDirections(trajet);
    }
/*
    private void getDirections(List<LatLng> waypoints) {
        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyAM62zi6ulD1s5mnU7H_5FoANiyqGhAZPk").build();
        DirectionsResult result;
        try {
            List<LatLng> waypointLatLngs = new ArrayList<>();
            for (LatLng waypoint : waypoints.subList(1, waypoints.size() - 1)) {
                waypointLatLngs.add(new LatLng(waypoint.latitude, waypoint.longitude));
            }

            result = DirectionsApi.newRequest(context)
                    .origin(new com.google.maps.model.LatLng(waypoints.get(0).latitude, waypoints.get(0).longitude))
                    .destination(new com.google.maps.model.LatLng(waypoints.get(waypoints.size() - 1).latitude, waypoints.get(waypoints.size() - 1).longitude))
                    .waypoints(waypointLatLngs.toArray(new com.google.maps.model.LatLng[0]))
                    .mode(TravelMode.DRIVING)
                    .optimizeWaypoints(true)
                    .await();

            if (result.routes != null && result.routes.length > 0) {
                DirectionsRoute route = result.routes[0];
                displayRoute(route);
                Toast.makeText(this, "Succès", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Échec", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

*/

    private void getDirections(List<LatLng> waypoints) {
        try {
            // Obtenir l'itinéraire entre le premier et le dernier point sans alternatives
            LatLng from = waypoints.get(0);
            LatLng to = waypoints.get(waypoints.size() - 1);

            // Construire l'URI avec les coordonnées du premier et du dernier point, en désactivant les alternatives
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" +
                    from.latitude + "," + from.longitude + "/" +
                    to.latitude + "," + to.longitude + "?dirflg=d");

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




    private void displayRoute(DirectionsRoute route) {
        PolylineOptions polylineOptions = new PolylineOptions();
        DirectionsLeg leg = route.legs[0];

        for (com.google.maps.model.LatLng point : leg.steps[0].polyline.decodePath()) {
            LatLng position = new LatLng(point.lat, point.lng);
            polylineOptions.add(position);
        }

        googleMap.addPolyline(polylineOptions);
    }
    private BitmapDescriptor getResizedBitmapDescriptor(int resourceId, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return BitmapDescriptorFactory.fromBitmap(resizedBitmap);
    }


    @Override
    public void onTrajetLoaded(List<Positions> positionList) {

    }

    @Override
    public void onFailedToLoadTrajet() {

    }
}
