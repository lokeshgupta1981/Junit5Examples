package com.howtodoinjava.junit.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.howtodoinjava.junit.suites")
@IncludeTags("fast")
public class FastSuite {
}
