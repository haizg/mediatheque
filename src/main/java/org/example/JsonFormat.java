package org.example;

import java.util.List;

public class JsonFormat implements FormatRapport {

    @Override
    public String generer(List<? extends Document> docs) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < docs.size(); i++) {
            Document d = docs.get(i);
            sb.append("  {\"titre\": \"").append(echapper(d.getTitre()))
                    .append("\", \"type\": \"").append(d.getClass().getSimpleName())
                    .append("\"}");
            if (i < docs.size() - 1) sb.append(",");
            sb.append("\n");
        }
        return sb.append("]\n").toString();
    }

    private String echapper(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}