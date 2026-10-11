package com.howtodoinjava.junit.suites;

import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectMethod;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectMethod("com.howtodoinjava.junit.suites.orders.OrderServiceTest#placesOrder")
@SelectMethod("com.howtodoinjava.junit.suites.payments.PaymentServiceTest#chargesValidCard")
@ConfigurationParameter(key = "junit.jupiter.execution.timeout.default", value = "5s")
public class SmokeSuite {

    @BeforeSuite
    static void startStubs() {
        System.out.println("Starting payment gateway stub");
    }

    @AfterSuite
    static void stopStubs() {
        System.out.println("Stopping payment gateway stub");
    }
}
