package com.howtodoinjava.junit.tags;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppStartupTest {

    @Test
    void createsScaler() {
        assertNotNull(new RecipeScaler());
    }
}
