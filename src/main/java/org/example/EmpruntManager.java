package org.example;

import java.time.LocalDate;

public class EmpruntManager {
    private final Catalogue<Document> catalogue; // COMPOSITION
    private int empruntsEnCours = 0;

    public EmpruntManager(Catalogue<Document> catalogue) {
        this.catalogue = catalogue;
    }

    public void emprunter(String titre) throws MediathequeException {
        // dependance cachee vers un singleton : on la corrige a l'etape 3
        ConfigurationMediatheque config = ConfigurationMediatheque.getInstance();
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
        return LocalDate.now().plusDays(ConfigurationMediatheque.getInstance().getDureeEmprunt());
    }
}