package com.howtodoinjava.junit.aftereach;

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
class AfterEachResultsTest {

  private static final String PKG = "com.howtodoinjava.junit.aftereach.demo.";

  @Test
  void secondTestFailsWithoutCleanup() {
    ReportCache.clear();
    EngineTestKit.engine("junit-jupiter")
        .selectors(selectClass(PKG + "NoCleanupDemo"))
        .execute()
        .testEvents()
        .assertStatistics(stats -> stats.started(2).succeeded(1).failed(1));
    ReportCache.clear();
  }

  @Test
  void afterEachRunsWhenTestFails() {
    EngineTestKit.engine("junit-jupiter")
        .selectors(selectClass(PKG + "FailingTestDemo"))
        .execute()
        .testEvents()
        .assertStatistics(stats -> stats.started(1).failed(1));
  }

  @Test
  void exceptionInAfterEachFailsTheTest() {
    EngineTestKit.engine("junit-jupiter")
        .selectors(selectClass(PKG + "FailingCleanupDemo"))
        .execute()
        .testEvents()
        .assertStatistics(stats -> stats.started(1).failed(1).succeeded(0));
  }

  @Test
  void abortedTestRunsAfterEachDisabledDoesNot() {
    EngineTestKit.engine("junit-jupiter")
        .selectors(selectClass(PKG + "SkippedTestsDemo"))
        .execute()
        .testEvents()
        .assertStatistics(stats -> stats.started(1).aborted(1).skipped(1));
  }

  @Test
  void staticAfterEachIsADiscoveryError() {
    List<DiscoveryIssue> issues = EngineTestKit.engine("junit-jupiter")
        .selectors(selectClass(PKG + "StaticAfterEachDemo"))
        .discover()
        .getDiscoveryIssues();
    assertEquals(DiscoveryIssue.Severity.ERROR, issues.getFirst().severity());
    assertTrue(issues.getFirst().message().contains("must not be static"));
  }
}
