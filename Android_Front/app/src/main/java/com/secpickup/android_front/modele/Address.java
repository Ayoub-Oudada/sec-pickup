package com.secpickup.android_front.modele;




import java.io.Serializable;
import java.util.List;


public class Address extends BaseEntity implements Serializable {
    private String rue;
    private String immeuble;
    private String codePostal;
    private List<Eleve> eleves;


    private double longitude;
    private double lattitude;

    private Trajet trajet;

    public double getLatitude() {
        return lattitude;
    }

    public Address(String rue, String immeuble, String codePostal, List<Eleve> eleves, double longitude, double lattitude, Trajet trajet) {
        this.rue = rue;
        this.immeuble = immeuble;
        this.codePostal = codePostal;
        this.eleves = eleves;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.trajet = trajet;
    }

    public double getLongitude() {
        return longitude;
    }
}
