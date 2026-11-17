package org.example;

import java.time.LocalDate;

public class EmpruntManager {
    private final Catalogue<Document> catalogue;         // COMPOSITION
    private final ConfigurationMediatheque config;       // INJECTEE (DIP)
    private int empruntsEnCours = 0;

    public EmpruntManager(Catalogue<Document> catalogue, ConfigurationMediatheque config) {
        this.catalogue = catalogue;
        this.config = config;
    }

    public void emprunter(String titre) throws MediathequeException {
        if (empruntsEnCours >= config.getMaxEmprunts()) {
            throw new DocumentIndisponibleException(
                    "Limite d'emprunts atteinte : " + config.getMaxEmprunts());
        }

        Document doc = catalogue.rechercherParTitre(titre)
                .orElseThrow(() -> new DocumentIntrouvableException("Document introuvable : " + titre));

        if (doc instanceof Empruntable e) {
            e.emprunter();
            empruntsEnCours++;
        } else {
            throw new DocumentIndisponibleException(
                    "Consultation sur place uniquement : " + titre);
        }
    }

    public LocalDate dateRetourPrevue() {
        return LocalDate.now().plusDays(config.getDureeEmprunt());
    }
}