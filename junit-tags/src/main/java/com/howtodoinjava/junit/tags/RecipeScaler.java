package com.howtodoinjava.junit.tags;

public class RecipeScaler {

    public double scale(double amount, int fromServings, int toServings) {
        if (fromServings <= 0 || toServings <= 0) {
            throw new IllegalArgumentException("Servings must be positive");
        }
        return amount * toServings / fromServings;
    }
}
