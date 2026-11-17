package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExportCatalogueTest {

    // Le MEME catalogue, exporte dans deux formats
    private Catalogue<Document> catalogue() {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Livre.Builder("Dune", "Herbert").build());
        c.ajouter(new Dvd("Matrix", 136));
        return c;
    }

    @Test
    void exportCsv() {
        String out = new ExportCatalogue(new FabriqueCsv()).exporter(catalogue());
        assertEquals("titre;type\nDune;Livre\nMatrix;Dvd\n", out);
    }

    @Test
    void exportHtml() {
        String out = new ExportCatalogue(new FabriqueHtml()).exporter(catalogue());
        assertEquals("<table>\n"
                + "  <tr><td>Dune</td><td>Livre</td></tr>\n"
                + "  <tr><td>Matrix</td><td>Dvd</td></tr>\n"
                + "</table>\n", out);
    }

    @Test
    void htmlEchappeLesCaracteresSpeciaux() {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Revue("Science & Vie", 1290));
        String out = new ExportCatalogue(new FabriqueHtml()).exporter(c);
        assertEquals(true, out.contains("Science &amp; Vie"));
    }
}