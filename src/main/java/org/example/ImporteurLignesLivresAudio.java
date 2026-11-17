package org.example;

public class ImporteurLignesLivresAudio extends Importeur {

    @Override
    protected Document creerDocument(String ligne) {
        String[] c = ligne.split(";");
        return new LivreAudio(c[0], c[1], Integer.parseInt(c[2]));
    }
}