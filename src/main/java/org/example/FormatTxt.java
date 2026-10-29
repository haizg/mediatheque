// FormatTxt.java
package org.example;

import java.util.List;

public class FormatTxt implements FormatRapport {
    @Override
    public String generer(List<? extends Document> docs) {
        StringBuilder sb = new StringBuilder();
        for (Document d : docs) {
            sb.append("- ").append(d.descriptionCourte()).append('\n');
        }
        return sb.toString();
    }
}