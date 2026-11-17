package org.example;

public class FabriqueMarkdown implements FabriqueExport {
    @Override public String enTete() { return "| titre | type |\n|---|---|\n"; }

    @Override public String ligne(Document d) {
        return "| " + d.getTitre() + " | " + d.getClass().getSimpleName() + " |\n";
    }

    @Override public String pied() { return ""; }
}