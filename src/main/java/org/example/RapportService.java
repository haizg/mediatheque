package org.example;

import java.util.List;

public class RapportService {
    private final FormatRapport format;
    private final SortieRapport sortie;
    private final Notificateur notificateur;

    public RapportService(FormatRapport format, SortieRapport sortie, Notificateur notificateur) {
        this.format = format;
        this.sortie = sortie;
        this.notificateur = notificateur;
    }

    public void genererRapport(List<? extends Document> docs) throws RapportException {
        String contenu = format.generer(docs);
        sortie.ecrire(contenu);
        notificateur.notifier("Rapport généré");   // pas atteint si l'écriture échoue
    }
}