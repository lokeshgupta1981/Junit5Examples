package com.howtodoinjava.legacy.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingListRepository {

  public List<String> findItems() {
    return List.of("milk", "eggs");
  }
}
