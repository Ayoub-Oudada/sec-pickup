package com.secpickup.android_front.modele;




import java.util.List;


public class Address extends BaseEntity {
    private String rue;
    private String immeuble;
    private String codePostal;


    private List<Eleve> eleves;


    private Trajet trajet;
}
