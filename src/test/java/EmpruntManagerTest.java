import org.example.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpruntManagerTest {

    private Catalogue<Document> catalogueAvecDune(Livre livre) {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(livre);
        c.ajouter(new Revue("Science & Vie", 1290));
        return c;
    }

    @Test
    void empruntNominal() throws MediathequeException {
        Livre dune = new Livre("Dune", "Herbert");
        new EmpruntManager(catalogueAvecDune(dune)).emprunter("Dune");
        assertTrue(dune.estEmprunte());
    }

    @Test
    void doubleEmpruntLeveExceptionAvecLeTitre() throws MediathequeException {
        Livre dune = new Livre("Dune", "Herbert");
        EmpruntManager manager = new EmpruntManager(catalogueAvecDune(dune));
        manager.emprunter("Dune");

        DocumentIndisponibleException e = assertThrows(
                DocumentIndisponibleException.class,
                () -> manager.emprunter("Dune"));
        assertTrue(e.getMessage().contains("Dune"));
    }

    @Test
    void documentIntrouvable() {
        EmpruntManager manager = new EmpruntManager(catalogueAvecDune(new Livre("Dune", "Herbert")));
        assertThrows(DocumentIntrouvableException.class, () -> manager.emprunter("Inconnu"));
    }

    @Test
    void revueNonEmpruntable() {
        EmpruntManager manager = new EmpruntManager(catalogueAvecDune(new Livre("Dune", "Herbert")));
        assertThrows(DocumentIndisponibleException.class, () -> manager.emprunter("Science & Vie"));
    }

    @Test
    void causeEstChainee() {
        Throwable cause = new IllegalArgumentException("source du problème");
        DocumentIndisponibleException e = new DocumentIndisponibleException("Échec", cause);
        assertSame(cause, e.getCause());
    }
}