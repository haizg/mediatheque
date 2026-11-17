package org.example;

public class Livre extends Document implements Empruntable {
    private final String auteur;
    private final String isbn;      // optionnels : null si absent
    private final String editeur;
    private final String langue;
    private final Integer nbPages;
    private boolean emprunte;

    // PROTECTED (pas private) : un futur LivreAudio doit pouvoir appeler super(builder).
    // La validation est faite ICI, donc aucun chemin ne contourne les regles.
    protected Livre(Builder b) {
        super(b.valider().titre);
        this.auteur = b.auteur;
        this.isbn = b.isbn;
        this.editeur = b.editeur;
        this.langue = b.langue;
        this.nbPages = b.nbPages;
    }

    // PROTOTYPE : constructeur de copie.
    // Les champs sont des String/Integer immuables : les partager est sans risque.
    // Choix metier : la copie est un NOUVEL exemplaire (nouvel id, non emprunte).
    public Livre(Livre original) {
        super(original.getTitre());
        this.auteur = original.auteur;
        this.isbn = original.isbn;
        this.editeur = original.editeur;
        this.langue = original.langue;
        this.nbPages = original.nbPages;
        // emprunte reste false : on ne copie pas l'etat d'emprunt
    }

    public static class Builder {
        private final String titre;     // requis
        private final String auteur;    // requis
        private String isbn;            // optionnels
        private String editeur;
        private String langue;
        private Integer nbPages;

        public Builder(String titre, String auteur) {
            this.titre = titre;
            this.auteur = auteur;
        }

        public Builder isbn(String isbn) { this.isbn = isbn; return this; }
        public Builder editeur(String editeur) { this.editeur = editeur; return this; }
        public Builder langue(String langue) { this.langue = langue; return this; }
        public Builder nbPages(int nbPages) { this.nbPages = nbPages; return this; }

        Builder valider() {
            if (auteur == null || auteur.isBlank()) {
                throw new IllegalStateException("L'auteur est obligatoire");
            }
            if (nbPages != null && nbPages <= 0) {
                throw new IllegalStateException("nbPages doit etre > 0");
            }
            if (isbn != null) {
                String chiffres = isbn.replace("-", "");
                if (!chiffres.matches("\\d{10}|\\d{13}")) {
                    throw new IllegalStateException("ISBN invalide : " + isbn);
                }
            }
            return this;
        }

        public Livre build() { return new Livre(this); }
    }

    public String getAuteur() { return auteur; }
    public String getIsbn() { return isbn; }
    public String getEditeur() { return editeur; }
    public String getLangue() { return langue; }
    public Integer getNbPages() { return nbPages; }

    @Override
    public void emprunter() throws DocumentIndisponibleException {
        if (emprunte) {
            throw new DocumentIndisponibleException("Livre déjà emprunté : " + getTitre());
        }
        emprunte = true;
    }

    @Override
    public void rendre() { emprunte = false; }

    @Override
    public boolean estEmprunte() { return emprunte; }

    @Override
    public String descriptionCourte() {
        return "Livre : " + getTitre() + " (" + auteur + ")";
    }
}