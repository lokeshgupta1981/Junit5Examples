package com.howtodoinjava.junit.autoclose;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import org.junit.jupiter.api.Test;
import org.junit.platform.testkit.engine.EngineTestKit;

/**
 * Runs the classes in the demo package with the JUnit Platform Test Kit and checks the results shown in the article.
 */
class AutoCloseResultsTest {

  private static final String PKG = "com.howtodoinjava.junit.autoclose.demo.";

  @Test
  void nullFieldIsSkipped() {
    run("NullFieldDemo").testEvents().assertStatistics(stats -> stats.succeeded(1));
  }

  @Test
  void exceptionInCloseFailsTheTest() {
    run("FailingCloseDemo").testEvents().assertStatistics(stats -> stats.started(1).failed(1));
  }

  @Test
  void fieldWithoutCloseMethodFailsTheTest() {
    run("InvalidFieldDemo").testEvents().assertStatistics(stats -> stats.started(1).failed(1));
  }

  @Test
  void primitiveFieldFailsTheTest() {
    run("PrimitiveFieldDemo").testEvents().assertStatistics(stats -> stats.started(1).failed(1));
  }

  private static org.junit.platform.testkit.engine.EngineExecutionResults run(String simpleName) {
    return EngineTestKit.engine("junit-jupiter").selectors(selectClass(PKG + simpleName)).execute();
  }
}
