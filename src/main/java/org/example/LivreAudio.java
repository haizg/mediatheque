package org.example;

public class LivreAudio extends Livre {
    private final int dureeMinutes;

    public LivreAudio(String titre, String auteur, int dureeMinutes) {
        super(new Livre.Builder(titre, auteur));   // meme validation que Livre
        this.dureeMinutes = dureeMinutes;
    }

    public int getDureeMinutes() { return dureeMinutes; }

    @Override
    public String descriptionCourte() {
        return "Livre audio : " + getTitre() + " (" + getAuteur() + ", " + dureeMinutes + " min)";
    }
}