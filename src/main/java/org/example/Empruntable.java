package org.example;

public interface Empruntable {
    void emprunter();          // IllegalStateException si déjà emprunté
    void rendre();
    boolean estEmprunte();
}