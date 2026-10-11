package com.howtodoinjava.junit.beforeall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.DiscoveryIssue;
import org.junit.platform.testkit.engine.EngineTestKit;

/**
 * Runs the classes in the demo package with the JUnit Platform Test Kit and checks the results shown in the article.
 */
class BeforeAllResultsTest {

  private static final String PKG = "com.howtodoinjava.junit.beforeall.demo.";

  @Test
  void nonStaticAfterAllIsADiscoveryError() {
    List<DiscoveryIssue> issues = EngineTestKit.engine("junit-jupiter")
        .selectors(selectClass(PKG + "NonStaticAfterAllDemo"))
        .discover()
        .getDiscoveryIssues();
    assertEquals(DiscoveryIssue.Severity.ERROR, issues.getFirst().severity());
    assertTrue(issues.getFirst().message().contains("must be static"));
  }

  @Test
  void failingBeforeAllRunsNoTest() {
    var results = EngineTestKit.engine("junit-jupiter")
        .selectors(selectClass(PKG + "FailingBeforeAllDemo"))
        .execute();
    results.testEvents().assertStatistics(stats -> stats.started(0));
    results.containerEvents().assertStatistics(stats -> stats.failed(1));
  }

  @Test
  void failingAfterAllFailsTheClass() {
    var results = EngineTestKit.engine("junit-jupiter")
        .selectors(selectClass(PKG + "FailingAfterAllDemo"))
        .execute();
    results.testEvents().assertStatistics(stats -> stats.succeeded(1));
    results.containerEvents().assertStatistics(stats -> stats.failed(1));
  }
}
