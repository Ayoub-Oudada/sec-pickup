package com.secpickup.android_front.modele;

import java.util.List;

public class Ecole extends BaseEntity {
    private String service;
    private String nom;
    private String numeroTelephone;
    private String email;
    private String siteWeb;
    private User user;
    private List<Assistante> assistantes;
    private List<Eleve> eleves;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }
// Constructors, getters, and setters as needed
}
