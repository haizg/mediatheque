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

        System.out.println("Max (ordre alphabétique) : "
                + Catalogue.max(catalogue.getItems()).descriptionCourte());

        EmpruntManager manager = new EmpruntManager(catalogue);
        try (RapportEmprunts rapport = new RapportEmprunts()) {
            manager.emprunter("Dune");
            rapport.enregistrer("Dune emprunté");
            manager.emprunter("Dune"); // double emprunt -> exception
        } catch (DocumentIndisponibleException e) {
            System.out.println("Indisponible : " + e.getMessage());
        } catch (MediathequeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}