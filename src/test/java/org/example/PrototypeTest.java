package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrototypeTest {

    private Livre original() {
        return new Livre.Builder("Clean Code", "R. Martin")
                .isbn("978-0132350884").editeur("Prentice Hall")
                .langue("en").nbPages(464).build();
    }

    @Test
    void laCopieAvecLesMemesValeurs() {
        Livre l = original();
        Livre copie = new Livre(l);

        assertNotSame(l, copie);
        assertEquals(l.getTitre(), copie.getTitre());
        assertEquals(l.getAuteur(), copie.getAuteur());
        assertEquals(l.getIsbn(), copie.getIsbn());
        assertEquals(l.getNbPages(), copie.getNbPages());
    }

    @Test
    void modifierLaCopieNeTouchePasLOriginal() throws DocumentIndisponibleException {
        Livre l = original();
        Livre copie = new Livre(l);

        copie.emprunter();

        assertTrue(copie.estEmprunte());
        assertFalse(l.estEmprunte());          // l'original est intact
        assertNotEquals(l.getId(), copie.getId());
    }


    @Test
    void copieSuperficielleDuCatalogue() throws DocumentIndisponibleException {
        Catalogue<Document> c1 = new Catalogue<>();
        Livre dune = new Livre.Builder("Dune", "Herbert").build();
        c1.ajouter(dune);

        Catalogue<Document> c2 = new Catalogue<>(c1);

        // la liste est independante
        c2.ajouter(new Dvd("Matrix", 136));
        assertEquals(1, c1.getItems().size());
        assertEquals(2, c2.getItems().size());

        // mais les documents sont partages
        dune.emprunter();
        Livre dansC2 = (Livre) c2.rechercherParTitre("Dune").orElseThrow();
        assertSame(dune, dansC2);
        assertTrue(dansC2.estEmprunte());
    }
}