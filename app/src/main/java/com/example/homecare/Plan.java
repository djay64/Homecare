package com.example.homecare;

public class Plan {
    public String id = "";
    public String libelle = "";
    public String date = "";

    public Plan() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Plan(String libelle, String date) {
        this.libelle = libelle;
        this.date = date;
    }

    //Les setters
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    //Les getters
    public String getLibelle() {
        return libelle;
    }
    public String getDate() {
        return date;
    }

    public String getId(){ return id; }


    @Override
    public String toString()  {
        return this.libelle ;
    }
}
