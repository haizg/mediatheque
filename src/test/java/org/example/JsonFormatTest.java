package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonFormatTest {

    @Test
    void genereUnTableauJson() {
        String json = new JsonFormat().generer(List.of(new Livre.Builder("Dune", "Herbert").build()));
        assertEquals("[\n  {\"titre\": \"Dune\", \"type\": \"Livre\"}\n]\n", json);
    }

    @Test
    void echappeLesGuillemets() {
        String json = new JsonFormat().generer(List.of(new Revue("Le \"Monde\"", 1)));
        assertTrue(json.contains("Le \\\"Monde\\\""));
    }

    @Test
    void fonctionneAvecRapportService() throws RapportException {
        java.util.List<String> ecrit = new java.util.ArrayList<>();
        RapportService service = new RapportService(new JsonFormat(), ecrit::add, m -> { });
        service.genererRapport(List.of(new Dvd("Matrix", 136)));
        assertTrue(ecrit.get(0).contains("Matrix"));
    }
}