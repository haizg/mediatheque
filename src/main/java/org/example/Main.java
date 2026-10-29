package org.example;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Document> docs = List.of(
                new Livre(1, "Dune", "Herbert"),
                new Livre(2, "1984", "Orwell"));

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