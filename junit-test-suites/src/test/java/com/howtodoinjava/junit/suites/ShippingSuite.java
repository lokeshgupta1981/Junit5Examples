package com.howtodoinjava.junit.suites;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.howtodoinjava.junit.suites.shipping")
@IncludeClassNamePatterns({".*Tests", ".*Checks"})
public class ShippingSuite {
}
