package org.example;

public class FabriqueCsv implements FabriqueExport {
    @Override public String enTete() { return "titre;type\n"; }

    @Override public String ligne(Document d) {
        return d.getTitre() + ";" + d.getClass().getSimpleName() + "\n";
    }

    @Override public String pied() { return ""; }
}