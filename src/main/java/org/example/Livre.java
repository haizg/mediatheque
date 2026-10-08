package org.example;

public class Livre extends Document implements Empruntable {
    private final String auteur;
    private boolean emprunte;

    public Livre(String titre, String auteur) {
        super(titre);
        this.auteur = auteur;
    }

    public String getAuteur() { return auteur; }

    @Override
    public void emprunter() {
        if (emprunte) throw new IllegalStateException("Livre déjà emprunté : " + getTitre());
        emprunte = true;
    }

    @Override
    public void rendre() { emprunte = false; }

    @Override
    public boolean estEmprunte() { return emprunte; }

    @Override
    public String descriptionCourte() {
        return "Livre : " + getTitre() + " (" + auteur + ")";
    }
}