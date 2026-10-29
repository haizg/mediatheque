// RapportException.java
package org.example;

public class RapportException extends Exception {
    public RapportException(String message, Throwable cause) {
        super(message, cause);   // on garde la cause d'origine (exception chaînée)
    }
}