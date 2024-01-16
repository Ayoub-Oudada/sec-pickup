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
=======
<<<<<<< HEAD

>>>>>>> 7650df02f568a954f51abff8bcab7178e258404f
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


<<<<<<< HEAD
    }
=======


=======
    private String username;

    public Positions(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
>>>>>>> dev
}
>>>>>>> 7650df02f568a954f51abff8bcab7178e258404f
