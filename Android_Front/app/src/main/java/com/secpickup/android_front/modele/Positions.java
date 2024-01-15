package com.secpickup.android_front.modele;



public class Positions extends BaseEntity{

    private double latitude;
    private double longitude;

    private String username;

    public Positions(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
