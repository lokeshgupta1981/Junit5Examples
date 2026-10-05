package com.howtodoinjava.mealplanner.domain.service;

import com.howtodoinjava.mealplanner.domain.model.Meal;
import java.util.List;

public class CalorieCalculator {

  public int total(List<Meal> meals) {
    return meals.stream().mapToInt(Meal::calories).sum();
  }
}
