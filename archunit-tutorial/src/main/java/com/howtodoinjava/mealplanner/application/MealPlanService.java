package com.howtodoinjava.mealplanner.application;

import com.howtodoinjava.mealplanner.domain.service.CalorieCalculator;
import com.howtodoinjava.mealplanner.domain.service.MealRepository;

public class MealPlanService {

  private final MealRepository meals;
  private final CalorieCalculator calculator = new CalorieCalculator();

  public MealPlanService(MealRepository meals) {
    this.meals = meals;
  }

  public int dailyCalories() {
    return calculator.total(meals.findAll());
  }
}
