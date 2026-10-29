// FormatJson.java
package org.example;

import java.util.List;

public class FormatJson implements FormatRapport {
    @Override
    public String generer(List<? extends Document> docs) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < docs.size(); i++) {
            Document d = docs.get(i);
            sb.append("  {\"id\": ").append(d.getId())
                    .append(", \"titre\": \"").append(d.getTitre().replace("\"", "\\\"")).append("\"}");
            sb.append(i < docs.size() - 1 ? ",\n" : "\n");
        }
        return sb.append("]\n").toString();
    }
}