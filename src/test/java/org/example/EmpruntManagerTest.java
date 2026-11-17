package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpruntManagerTest {

    private Catalogue<Document> catalogueAvecDune(Livre livre) {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(livre);
        c.ajouter(new Revue("Science & Vie", 1290));
        return c;
    }

    private Livre dune() {
        return new Livre.Builder("Dune", "Herbert").build();
    }

    private EmpruntManager manager(Catalogue<Document> c) {
        return new EmpruntManager(c, new ConfigurationMediatheque(21, 5));
    }

    @Test
    void empruntNominal() throws MediathequeException {
        Livre dune = dune();
        manager(catalogueAvecDune(dune)).emprunter("Dune");
        assertTrue(dune.estEmprunte());
    }

    @Test
    void doubleEmpruntLeveExceptionAvecLeTitre() throws MediathequeException {
        EmpruntManager manager = manager(catalogueAvecDune(dune()));
        manager.emprunter("Dune");

        DocumentIndisponibleException e = assertThrows(
                DocumentIndisponibleException.class,
                () -> manager.emprunter("Dune"));
        assertTrue(e.getMessage().contains("Dune"));
    }

    @Test
    void documentIntrouvable() {
        EmpruntManager manager = manager(catalogueAvecDune(dune()));
        assertThrows(DocumentIntrouvableException.class, () -> manager.emprunter("Inconnu"));
    }

    @Test
    void revueNonEmpruntable() {
        EmpruntManager manager = manager(catalogueAvecDune(dune()));
        assertThrows(DocumentIndisponibleException.class, () -> manager.emprunter("Science & Vie"));
    }

    @Test
    void causeEstChainee() {
        Throwable cause = new IllegalArgumentException("source du problème");
        DocumentIndisponibleException e = new DocumentIndisponibleException("Échec", cause);
        assertSame(cause, e.getCause());
    }
}