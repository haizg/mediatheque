// FormatCsv.java
package org.example;

import java.util.List;

public class FormatCsv implements FormatRapport {
    @Override
    public String generer(List<? extends Document> docs) {
        StringBuilder sb = new StringBuilder();               // fixes #6
        for (Document d : docs) {
            sb.append(d.getTitre()).append(';').append(d.getId()).append('\n');
        }
        return sb.toString();
    }
}