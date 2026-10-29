package org.example;

public class ServeurSmtp {
    private final String hote;

    public ServeurSmtp(String hote) { this.hote = hote; }

    public void envoyer(String destinataire, String message) {
        System.out.println("[SMTP " + hote + "] -> " + destinataire + " : " + message);
    }
}