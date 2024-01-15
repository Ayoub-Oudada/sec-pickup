package com.secpickup.android_front.modele;

import java.util.ArrayList;
import java.util.List;

public class Trajet extends BaseEntity {

    private String libTrajet;

    private List<Address> addresses;
    private List<Assistante> assistantes;
    private List<Rue> rue;

    // Constructeur et autres membres de la classe...

    public Trajet() {
        addresses = new ArrayList<>();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }






}
