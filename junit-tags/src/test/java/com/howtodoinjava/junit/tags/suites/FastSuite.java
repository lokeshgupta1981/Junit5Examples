package com.howtodoinjava.junit.tags.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.howtodoinjava.junit.tags")
@IncludeTags("fast")
class FastSuite {
}
