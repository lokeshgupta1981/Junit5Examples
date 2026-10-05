package com.howtodoinjava.mealplanner.domain.service;

import com.howtodoinjava.mealplanner.domain.model.Meal;
import java.util.List;

public interface MealRepository {

  List<Meal> findAll();
}
