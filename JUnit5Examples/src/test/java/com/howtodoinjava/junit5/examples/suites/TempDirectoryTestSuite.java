package com.howtodoinjava.junit5.examples.suites;

import org.junit.jupiter.api.Order;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.howtodoinjava.junit5.examples.TempDirTests;

@Suite
@SelectClasses(TempDirTests.class)
public class TempDirectoryTestSuite {

}
