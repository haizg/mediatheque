package org.example;

public interface Empruntable {
    void emprunter()throws DocumentIndisponibleException;
    void rendre();
    boolean estEmprunte();
}