package com.secpickup.android_front.modele;


import com.google.android.gms.maps.model.LatLng;

public class Positions extends BaseEntity{

    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    private String username;

    public Positions(double latitude, double longitude,String username) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.username =username;
    }


    }
