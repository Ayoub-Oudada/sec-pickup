package com.secpickup.android_front.modele;


import java.io.Serializable;

public class Positions extends BaseEntity implements Serializable {

    private double latitude;
    private double longitude;

    private String username;

    public Positions(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
