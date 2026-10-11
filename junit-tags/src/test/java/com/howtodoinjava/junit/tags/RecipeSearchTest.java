package com.howtodoinjava.junit.tags;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("integration")
@Tag("recipes")
class RecipeSearchTest {

    private final List<String> recipes = List.of("Pancakes", "Tomato soup", "Tomato pasta");

    @Test
    void findsByIngredient() {
        long found = recipes.stream().filter(r -> r.contains("Tomato")).count();
        assertEquals(2, found);
    }

    @Test
    @Tag("slow")
    void rebuildsSearchIndex() {
        List<String> index = recipes.stream().map(String::toLowerCase).sorted().toList();
        assertEquals("pancakes", index.getFirst());
    }
}
