package com.howtodoinjava.junit.suites;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.howtodoinjava.junit.suites.payments")
@ExcludePackages("com.howtodoinjava.junit.suites.payments.refunds")
public class PaymentsWithoutRefundsSuite {
}
