package com.howtodoinjava.junit5.examples;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.howtodoinjava.junit5.examples")
@IncludeTags("production")
public class ProductionTests 
{
}