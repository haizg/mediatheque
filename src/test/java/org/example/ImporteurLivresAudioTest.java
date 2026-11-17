package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImporteurLivresAudioTest {

    @Test
    void importeDesLivresAudio() {
        List<Document> docs = new ImporteurLignesLivresAudio()
                .importer(List.of("Dune;Herbert;1260", "1984;Orwell;900"));

        assertEquals(2, docs.size());
        LivreAudio premier = assertInstanceOf(LivreAudio.class, docs.get(0));
        assertEquals(1260, premier.getDureeMinutes());
    }

    @Test
    void livreAudioEstUnLivreEmpruntable() {
        LivreAudio la = new LivreAudio("Dune", "Herbert", 1260);
        assertInstanceOf(Livre.class, la);
        assertFalse(la.estEmprunte());
    }

    @Test
    void livreAudioPasseParLaValidation() {
        assertThrows(IllegalStateException.class,
                () -> new LivreAudio("Dune", " ", 100));
    }
}