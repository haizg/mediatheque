package org.example;

public abstract class Document implements Comparable<Document> {
    private final String titre;
    private static int compteur = 0;
    private final int id = ++compteur;
    protected Document(String titre) {
        if (titre == null || titre.isBlank()) {
            throw new IllegalArgumentException("Le titre est obligatoire");
        }
        this.titre = titre;
    }
    public int getId() { return id; }

    public String getTitre() { return titre; }

    public abstract String descriptionCourte();

    @Override
    public int compareTo(Document autre) {
        return titre.compareToIgnoreCase(autre.titre);
    }

    @Override
    public String toString() { return descriptionCourte(); }
}