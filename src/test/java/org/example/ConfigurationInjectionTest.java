package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

// Plus d'etat global : chaque test cree SA configuration.
// Les tests passent dans n'importe quel ordre.
class ConfigurationInjectionTest {

    private Catalogue<Document> catalogue() {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Livre.Builder("Dune", "Herbert").build());
        c.ajouter(new Livre.Builder("1984", "Orwell").build());
        return c;
    }

    @Test
    void dureePersonnalisee() {
        EmpruntManager manager = new EmpruntManager(catalogue(), new ConfigurationMediatheque(7, 5));
        assertEquals(LocalDate.now().plusDays(7), manager.dateRetourPrevue());
    }

    @Test
    void dureeParDefaut() {
        EmpruntManager manager = new EmpruntManager(catalogue(), new ConfigurationMediatheque(21, 5));
        assertEquals(LocalDate.now().plusDays(21), manager.dateRetourPrevue());
    }

    @Test
    void limiteDEmpruntsRespectee() throws MediathequeException {
        EmpruntManager manager = new EmpruntManager(catalogue(), new ConfigurationMediatheque(21, 1));
        manager.emprunter("Dune");
        assertThrows(DocumentIndisponibleException.class, () -> manager.emprunter("1984"));
    }
}