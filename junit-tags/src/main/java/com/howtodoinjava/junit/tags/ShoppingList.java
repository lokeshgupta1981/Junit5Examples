package com.howtodoinjava.junit.tags;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingList {

    private final Map<String, Integer> items = new LinkedHashMap<>();

    public void add(String item, int quantity) {
        items.merge(item, quantity, Integer::sum);
    }

    public int quantityOf(String item) {
        return items.getOrDefault(item, 0);
    }

    public String exportAsText() {
        return items.entrySet().stream()
                .map(e -> e.getValue() + " x " + e.getKey())
                .collect(Collectors.joining("\n"));
    }
}
