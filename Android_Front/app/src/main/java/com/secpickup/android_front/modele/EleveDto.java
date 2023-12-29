package com.secpickup.android_front.modele;



import java.util.Date;
import java.util.List;


public class EleveDto {
    private Long id;


    private String nom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getSituation() {
        return situation;
    }


    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }

    public Rue getRue() {
        return rue;
    }

    public void setRue(Rue rue) {
        this.rue = rue;
    }

    public String getPosition() {
        return position;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    private String prenom;


    private String cne;
    private Date dateDeNaissance;
    private int niveau;
    private String domicile;

    private String position;
    private Parent parent;
    private String situation;
    private Ecole ecole;
    private Rue rue;

    public EleveDto(String situation) {
        this.situation = situation;
    }









}
