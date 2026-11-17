package org.example;

public class ExportCatalogue {
    private final FabriqueExport fabrique;   // injectee (DIP)

    public ExportCatalogue(FabriqueExport fabrique) {
        this.fabrique = fabrique;
    }

    public String exporter(Iterable<? extends Document> docs) {
        StringBuilder sb = new StringBuilder(fabrique.enTete());
        for (Document d : docs) {
            sb.append(fabrique.ligne(d));
        }
        return sb.append(fabrique.pied()).toString();
    }
}