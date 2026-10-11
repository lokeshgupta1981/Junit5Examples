package com.howtodoinjava.junit.suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({CheckoutSuite.class, PaymentsSuite.class})
public class ReleaseSuite {
}
