package com.howtodoinjava.junit.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("fast")
@Tag("recipes")
class RecipeScalerTest {

    private final RecipeScaler scaler = new RecipeScaler();

    @Test
    void doublesServings() {
        assertEquals(400.0, scaler.scale(200, 2, 4));
    }

    @Test
    void rejectsZeroServings() {
        assertThrows(IllegalArgumentException.class, () -> scaler.scale(200, 0, 4));
    }
}
