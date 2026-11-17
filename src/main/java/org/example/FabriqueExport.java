package org.example;

// ABSTRACT FACTORY : une famille (en-tete, ligne, pied) toujours coherente
public interface FabriqueExport {
    String enTete();
    String ligne(Document d);
    String pied();
}