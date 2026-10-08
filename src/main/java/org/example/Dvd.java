package org.example;

public class Dvd extends Document implements Empruntable {
    private final int dureeMinutes;
    private boolean emprunte;

    public Dvd(String titre, int dureeMinutes) {
        super(titre);
        this.dureeMinutes = dureeMinutes;
    }

    @Override
    public void emprunter() throws DocumentIndisponibleException {
        if (emprunte) {
            throw new DocumentIndisponibleException("DVD déjà emprunté : " + getTitre());
        }
        emprunte = true;
    }

    @Override
    public void rendre() { emprunte = false; }

    @Override
    public boolean estEmprunte() { return emprunte; }

    @Override
    public String descriptionCourte() {
        return "DVD : " + getTitre() + " (" + dureeMinutes + " min)";
    }
}