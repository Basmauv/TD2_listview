package com.example.basilemauvieux.td2_listview.Entity;

import android.support.annotation.NonNull;

public class Movie {

    private String nom;

    private String realisateur;

    private String producteur;

    private int annee;

    private String affiche;

    private int cout;

    public Movie() {
    }

    public Movie(String nom, String realisateur, String producteur, int annee, int cout) {
        this.nom = nom;
        this.realisateur = realisateur;
        this.producteur = producteur;
        this.annee = annee;
        this.cout = cout;
    }

    public String getNom() {
        return nom;
    }

    public Movie setNom(String nom) {
        this.nom = nom;

        return this;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public Movie setRealisateur(String realisateur) {
        this.realisateur = realisateur;

        return this;
    }

    public String getProducteur() {
        return producteur;
    }

    public Movie setProducteur(String producteur) {
        this.producteur = producteur;

        return this;
    }

    public int getAnnee() {
        return annee;
    }

    public Movie setAnnee(int annee) {
        this.annee = annee;

        return this;
    }

    public String getAffiche() {
        return affiche;
    }

    public Movie setAffiche(String affiche) {
        this.affiche = affiche;

        return this;
    }

    public int getCout() {
        return cout;
    }

    public Movie setCout(int cout) {
        this.cout = cout;

        return this;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getNom() + "\n" + this.getProducteur() + "\n" + this.getRealisateur() + "\n" + this.getAnnee();
    }
}
