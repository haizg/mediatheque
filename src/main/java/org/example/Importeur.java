package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Importeur {

    // Squelette commun, ecrit UNE fois : final, personne ne le redefinit
    public final List<Document> importer(List<String> lignes) {
        List<Document> docs = new ArrayList<>();
        for (String ligne : lignes) {
            if (!ligne.isBlank()) {
                docs.add(creerDocument(ligne));   // <- FACTORY METHOD
            }
        }
        return docs;
    }

    protected abstract Document creerDocument(String ligne);
}