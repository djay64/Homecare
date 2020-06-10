package com.example.homecare;

public class User {

    public String nom;
    public String prenom;

    public User() {
      // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
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

}