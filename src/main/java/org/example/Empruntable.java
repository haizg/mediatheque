package org.example;

public class Main {
    public static void main(String[] args) {
        Catalogue<Document> catalogue = new Catalogue<>();
        catalogue.ajouter(new Livre("Dune", "Herbert"));
        catalogue.ajouter(new Dvd("Matrix", 136));
        catalogue.ajouter(new Revue("Science & Vie", 1290));

        catalogue.afficherTout();

        System.out.println(catalogue.rechercherParTitre("dune")
                .map(Document::descriptionCourte)
                .orElse("Introuvable"));

        System.out.println("Max (ordre alphabétique) : " + Outils.max(catalogue.getItems()));

        Livre l = new Livre("1984", "Orwell");
        l.emprunter();
        try {
            l.emprunter();
        } catch (IllegalStateException e) {
            System.out.println("Erreur attendue : " + e.getMessage());
        }
    }
}