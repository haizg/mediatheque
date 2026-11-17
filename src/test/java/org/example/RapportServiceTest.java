package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RapportServiceTest {

    @Test
    void genereEcritEtNotifieSansRienDeConcret() throws RapportException {
        List<String> ecrit = new ArrayList<>();
        List<String> messages = new ArrayList<>();

        FormatRapport fauxFormat = docs -> "CONTENU-FAUX";
        SortieRapport fausseSortie = ecrit::add;       // pas de fichier
        Notificateur fauxNotificateur = messages::add; // pas de SMTP

        RapportService service =
                new RapportService(fauxFormat, fausseSortie, fauxNotificateur);
        service.genererRapport(List.of(new Livre.Builder("Dune", "Herbert").build()));

        assertEquals(List.of("CONTENU-FAUX"), ecrit);
        assertEquals(1, messages.size());
    }
}