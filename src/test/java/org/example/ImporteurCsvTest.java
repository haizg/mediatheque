package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImporteurCsvTest {

    @Test
    void creeLesTroisTypes() {
        List<Document> docs = new ImporteurCsv().importer(List.of(
                "livre;Dune;Herbert",
                "dvd;Matrix;136",
                "revue;Science & Vie;1290"));

        assertEquals(3, docs.size());
        assertInstanceOf(Livre.class, docs.get(0));
        assertInstanceOf(Dvd.class, docs.get(1));
        assertInstanceOf(Revue.class, docs.get(2));
    }

    @Test
    void ignoreLesLignesVides() {
        List<Document> docs = new ImporteurCsv().importer(List.of("livre;Dune;Herbert", "  ", ""));
        assertEquals(1, docs.size());
    }

    @Test
    void typeInconnuLeveException() {
        assertThrows(IllegalArgumentException.class,
                () -> new ImporteurCsv().importer(List.of("cd;Truc;1")));
    }
}