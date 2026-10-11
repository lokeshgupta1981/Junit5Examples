package com.howtodoinjava.junit.suites.payments;

import java.math.BigDecimal;

public class PaymentService {

    public boolean isValidCard(String number) {
        return number != null && number.matches("\\d{16}");
    }

    public String charge(String card, BigDecimal amount) {
        if (!isValidCard(card)) {
            return "DECLINED";
        }
        return amount.signum() > 0 ? "CHARGED" : "DECLINED";
    }
}
