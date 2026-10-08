package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class Catalogue<T extends Document> {
    private final List<T> elements = new ArrayList<>();

    public void ajouter(T element) {
        elements.add(Objects.requireNonNull(element));
    }

    public Optional<T> rechercherParTitre(String titre) {
        return elements.stream()
                .filter(e -> e.getTitre().equalsIgnoreCase(titre))
                .findFirst();
    }

    public void afficherTout() {
        elements.forEach(System.out::println);
    }

    public List<T> getItems() {
        return Collections.unmodifiableList(elements);
    }

    public static <T extends Comparable<? super T>> T max(List<T> liste) {
        if (liste.isEmpty()) throw new NoSuchElementException("Liste vide");
        T m = liste.getFirst();
        for (T e : liste) {
            if (e.compareTo(m) > 0) m = e;
        }
        return m;
    }
}