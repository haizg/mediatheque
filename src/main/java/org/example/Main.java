package org.example;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Document> docs = List.of(
                new Livre.Builder("Dune", "Herbert").build(),
                new Livre.Builder("1984", "Orwell").build());

        // --- Assemblage de la partie emprunts ---
        Catalogue<Document> catalogue = new Catalogue<>();
        docs.forEach(catalogue::ajouter);

        ConfigurationMediatheque config = new ConfigurationMediatheque(21, 5); // l'unicité se décide ICI, à l'assemblage
        EmpruntManager manager = new EmpruntManager(catalogue, config);

        try {
            manager.emprunter("Dune");
            System.out.println("Retour prévu le : " + manager.dateRetourPrevue());
        } catch (MediathequeException e) {
            System.err.println("Erreur emprunt : " + e.getMessage());
        }

        // --- Assemblage de la partie rapport ---
        RapportService service = new RapportService(
                new FormatCsv(),
                new SortieFichier(Path.of("rapport.csv")),
                new NotificateurSmtp(new ServeurSmtp("smtp.itbs.tn"), "direction@itbs.tn"));

        try {
            service.genererRapport(docs);
        } catch (RapportException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}