package org.example;

import java.util.ArrayList;
import java.util.List;

public class RapportEmprunts implements AutoCloseable {
    private final List<String> lignes = new ArrayList<>();

    public void enregistrer(String ligne) {
        lignes.add(ligne);
    }

    @Override
    public void close() {
        System.out.println("=== Rapport des emprunts (" + lignes.size() + ") ===");
        lignes.forEach(System.out::println);
    }
}