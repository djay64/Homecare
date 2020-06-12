package com.example.homecare;

public class Bien {
    public String id = "";
    public String adresse = "";
    public String ville = "";
    public String codePostal = "";
    public String dateConstruction = "";
    public String surface = "";

    public Bien() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Bien(String adresse, String ville, String codePostal, String dateConstruction, String surface) {
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
        this.dateConstruction = dateConstruction;
        this.surface = surface;
    }

    //Les setters
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    public void setDateConstruction(String dateConstruction) {
        this.dateConstruction = dateConstruction;
    }
    public void setSurface(String surface) {
        this.surface = surface;
    }
    public void setId(String id) {
        this.id = id;
    }

    //Les getters
    public String getAdresse() {
        return adresse;
    }
    public String getVille() {
        return ville;
    }
    public String getCodePostal() {
        return codePostal;
    }
    public String getDateConstruction() {
        return dateConstruction;
    }
    public String getSurface() {
        return surface;
    }
    public String getId(){ return id; }


    @Override
    public String toString()  {
        return this.codePostal +" (Ville: "+ this.ville +")";
    }
}
