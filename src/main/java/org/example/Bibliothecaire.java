package org.example;

import java.util.Optional;

public class Bibliothecaire {
    private final String nom;
    private final Catalogue<Document> catalogue; // COMPOSITION

    public Bibliothecaire(String nom, Catalogue<Document> catalogue) {
        this.nom = nom;
        this.catalogue = catalogue;
    }

    public void accueillir() {
        System.out.println("Bonjour, je suis " + nom);
    }

    // DELEGATION : on n'expose que ce qui a du sens pour un bibliothécaire
    public Optional<Document> chercher(String titre) {
        return catalogue.rechercherParTitre(titre);
    }

    public void afficherCatalogue() {
        catalogue.afficherTout();
    }
}