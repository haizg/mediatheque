package org.example;

public class Dvd extends Document implements Empruntable {
    private final int duree;
    private boolean emprunte;

    public Dvd(String titre, int duree) {
        super(titre);
        this.duree = duree;
    }

    @Override
    public String descriptionCourte() {
        return "DVD : " + getTitre() + " (" + duree + " min)";
    }

    @Override
    public void emprunter() {
        if (emprunte) throw new IllegalStateException("DVD déjà emprunté : " + getTitre());
        emprunte = true;
    }

    @Override
    public void rendre() { emprunte = false; }

    @Override
    public boolean estEmprunte() { return emprunte; }
}