package org.example;

import java.util.List;

public interface FormatRapport {
    String generer(List<? extends Document> docs);
}