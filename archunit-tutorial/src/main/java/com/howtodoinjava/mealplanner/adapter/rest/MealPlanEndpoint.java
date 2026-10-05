package com.howtodoinjava.mealplanner.adapter.rest;

import com.howtodoinjava.mealplanner.application.MealPlanService;

public class MealPlanEndpoint {

  private final MealPlanService service;

  public MealPlanEndpoint(MealPlanService service) {
    this.service = service;
  }

  public String today() {
    return "calories=" + service.dailyCalories();
  }
}
