package org.example;

public class ImporteurCsv extends Importeur {

    @Override
    protected Document creerDocument(String ligne) {
        String[] c = ligne.split(";");
        return switch (c[0]) {
            case "livre" -> new Livre.Builder(c[1], c[2]).build();   // titre;auteur
            case "dvd"   -> new Dvd(c[1], Integer.parseInt(c[2]));   // titre;duree
            case "revue" -> new Revue(c[1], Integer.parseInt(c[2])); // titre;numero
            default -> throw new IllegalArgumentException("Type : " + c[0]);
        };
    }
}