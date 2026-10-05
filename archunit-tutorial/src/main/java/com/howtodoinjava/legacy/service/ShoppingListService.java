package com.howtodoinjava.legacy.service;

import com.howtodoinjava.legacy.repository.ShoppingListRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

  private final ShoppingListRepository repository;

  public ShoppingListService(ShoppingListRepository repository) {
    this.repository = repository;
  }

  public List<String> items() {
    return repository.findItems();
  }
}
