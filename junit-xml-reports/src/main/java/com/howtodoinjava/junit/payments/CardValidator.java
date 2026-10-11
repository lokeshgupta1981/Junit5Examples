package com.howtodoinjava.junit.payments;

public class CardValidator {

  public boolean passesLuhnCheck(String cardNumber) {
    String digits = cardNumber.replace(" ", "");
    if (!digits.matches("\\d{12,19}")) {
      return false;
    }
    int sum = 0;
    boolean doubleIt = false;
    for (int i = digits.length() - 1; i >= 0; i--) {
      int d = digits.charAt(i) - '0';
      if (doubleIt) {
        d *= 2;
        if (d > 9) {
          d -= 9;
        }
      }
      sum += d;
      doubleIt = !doubleIt;
    }
    return sum % 10 == 0;
  }

  public String mask(String cardNumber) {
    String digits = cardNumber.replace(" ", "");
    return "**** " + digits.substring(digits.length() - 4);
  }
}
