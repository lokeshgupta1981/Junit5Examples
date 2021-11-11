package com.howtodoinjava.junit5.examples.suites;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;

@SelectPackages({"com.howtodoinjava.junit5.examples.packageA"
				,"com.howtodoinjava.junit5.examples.packageB"})
@IncludeClassNamePatterns({"^.*ATests?$"})
@IncludeTags("production")
public class JUnit5TestSuiteExample {

}
