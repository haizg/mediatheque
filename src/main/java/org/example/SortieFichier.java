// SortieFichier.java
package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SortieFichier implements SortieRapport {
    private final Path chemin;                       // plus de chemin en dur (#3)

    public SortieFichier(Path chemin) { this.chemin = chemin; }

    @Override
    public void ecrire(String contenu) throws RapportException {
        try {
            Files.writeString(chemin, contenu);
        } catch (IOException e) {
            throw new RapportException("Écriture impossible : " + chemin, e);
        }
    }
}