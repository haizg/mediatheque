package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Catalogue<T extends Document> {
    private final List<T> items = new ArrayList<>();

    public void ajouter(T item) { items.add(item); }

    public Optional<T> rechercherParTitre(String titre) {
        return items.stream()
                .filter(d -> d.getTitre().equalsIgnoreCase(titre))
                .findFirst();
    }

    public void afficherTout() {
        items.forEach(d -> System.out.println(d.descriptionCourte()));
    }

    public List<T> getItems() { return List.copyOf(items); }
}