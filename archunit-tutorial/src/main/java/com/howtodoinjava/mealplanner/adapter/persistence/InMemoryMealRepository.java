package com.howtodoinjava.mealplanner.adapter.persistence;

import com.howtodoinjava.mealplanner.domain.model.Meal;
import com.howtodoinjava.mealplanner.domain.service.MealRepository;
import java.util.List;

public class InMemoryMealRepository implements MealRepository {

  @Override
  public List<Meal> findAll() {
    return List.of(new Meal("oats", 300), new Meal("salad", 250));
  }
}
