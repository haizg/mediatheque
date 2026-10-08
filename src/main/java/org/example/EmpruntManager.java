package org.example;

public class EmpruntManager {
    private final Catalogue<Document> catalogue; // COMPOSITION

    public EmpruntManager(Catalogue<Document> catalogue) {
        this.catalogue = catalogue;
    }

    public void emprunter(String titre) throws MediathequeException {
        // DELEGATION de la recherche au catalogue
        Document doc = catalogue.rechercherParTitre(titre)
                .orElseThrow(() -> new DocumentIntrouvableException("Document introuvable : " + titre));

        if (doc instanceof Empruntable e) {
            e.emprunter();
        } else {
            throw new DocumentIndisponibleException(
                    "Consultation sur place uniquement : " + titre);
        }
    }
}