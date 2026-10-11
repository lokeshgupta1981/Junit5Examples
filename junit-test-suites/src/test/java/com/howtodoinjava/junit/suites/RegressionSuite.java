package com.howtodoinjava.junit.suites;

import org.junit.platform.suite.api.ExcludeClassNamePatterns;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.howtodoinjava.junit.suites")
@ExcludeTags("slow")
@ExcludeClassNamePatterns(".*Shipping.*")
public class RegressionSuite {
}
