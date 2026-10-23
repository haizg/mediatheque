package org.example;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CatalogueIterationTest {

    private Catalogue<Document> catalogue() {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Livre("Dune", "Herbert"));
        c.ajouter(new Dvd("Matrix", 136));
        return c;
    }

    @Test
    void forEachParcourtTousLesDocuments() {
        int n = 0;
        for (Document d : catalogue()) n++;
        assertEquals(2, n);
    }

    @Test
    void parcoursEnLectureSeule() {
        Iterator<Document> it = catalogue().iterator();
        it.next();
        assertThrows(UnsupportedOperationException.class, it::remove);
    }
}