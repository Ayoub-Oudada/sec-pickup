package com.secpickup.android_front.modele;


<<<<<<< HEAD
import com.google.android.gms.maps.model.LatLng;

public class Positions extends BaseEntity{
=======
import java.io.Serializable;

public class Positions extends BaseEntity implements Serializable {
>>>>>>> dev

    private double latitude;
    private double longitude;

<<<<<<< HEAD

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




=======
    private String username;

    public Positions(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
>>>>>>> dev
}
