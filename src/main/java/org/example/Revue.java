package org.example;

public class Revue extends Document {
    private final int numero;

    public Revue(String titre, int numero) {
        super(titre);
        this.numero = numero;
    }

    @Override
    public String descriptionCourte() {
        return "Revue : " + getTitre() + " n°" + numero;
    }
}