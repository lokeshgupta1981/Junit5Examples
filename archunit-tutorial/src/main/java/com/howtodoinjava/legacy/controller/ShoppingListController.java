package com.howtodoinjava.legacy.controller;

import com.howtodoinjava.legacy.repository.ShoppingListRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingListController {

  @Autowired
  private ShoppingListRepository repository;   // field injection, skips the service layer

  @GetMapping("/items")
  public List<String> items() {
    return repository.findItems();
  }
}
