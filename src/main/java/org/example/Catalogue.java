package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class Catalogue<T extends Document> implements Iterable<T> {
    private final List<T> elements = new ArrayList<>();

    public void ajouter(T element) {
        elements.add(Objects.requireNonNull(element));
    }

    public Optional<T> rechercherParTitre(String titre) {
        return elements.stream()
                .filter(e -> e.getTitre().equalsIgnoreCase(titre))
                .findFirst();
    }

    // PATTERN ITERATOR : parcours sans exposer la représentation interne
    @Override
    public Iterator<T> iterator() {
        return Collections.unmodifiableList(elements).iterator(); // délégation
    }

    public List<T> getItems() {
        return Collections.unmodifiableList(elements);
    }

    public static <T extends Comparable<? super T>> T max(List<T> liste) {
        if (liste.isEmpty()) throw new NoSuchElementException("Liste vide");
        T m = liste.get(0);
        for (T e : liste) {
            if (e.compareTo(m) > 0) m = e;
        }
        return m;
    }

    // Bonus (étape 5)
    public Stream<T> stream() {
        return elements.stream();
    }
}