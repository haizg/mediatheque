package org.example;

// Choix : CHECKED. Emprunter un document déjà emprunté est une situation
// métier attendue et récupérable : l'appelant peut réagir (proposer un autre
// document, réserver...). Le compilateur force donc à la traiter.
public abstract class MediathequeException extends Exception {
    protected MediathequeException(String message) {
        super(message);
    }

    protected MediathequeException(String message, Throwable cause) {
        super(message, cause);
    }
}