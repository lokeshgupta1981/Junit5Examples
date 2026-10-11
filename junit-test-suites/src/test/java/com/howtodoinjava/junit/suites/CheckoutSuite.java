package com.howtodoinjava.junit.suites;

import com.howtodoinjava.junit.suites.orders.OrderServiceTest;
import com.howtodoinjava.junit.suites.payments.PaymentServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Checkout tests")
@SelectClasses({OrderServiceTest.class, PaymentServiceTest.class})
public class CheckoutSuite {
}
