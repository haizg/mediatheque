package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FabriqueMarkdownTest {
    @Test
    void exportMarkdown() {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Livre.Builder("Dune", "Herbert").build());
        String out = new ExportCatalogue(new FabriqueMarkdown()).exporter(c);
        assertEquals("| titre | type |\n|---|---|\n| Dune | Livre |\n", out);
    }
}