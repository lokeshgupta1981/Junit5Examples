package com.howtodoinjava.junit5.examples;

import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;

@SelectPackages("com.howtodoinjava.junit5.examples")
@IncludeTags("dev")
@ExcludeTags({"prod","sit"})
public class MultipleTagsExample {

}
