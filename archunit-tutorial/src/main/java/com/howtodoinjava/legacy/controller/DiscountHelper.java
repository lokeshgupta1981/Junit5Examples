package com.howtodoinjava.legacy.controller;

import org.springframework.stereotype.Service;

@Service
public class DiscountHelper {

  public int discount(int price) {
    return price / 10;
  }
}
