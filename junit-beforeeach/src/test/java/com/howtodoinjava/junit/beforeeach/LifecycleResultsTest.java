package com.howtodoinjava.junit.beforeeach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.DiscoveryIssue;
import org.junit.platform.testkit.engine.EngineTestKit;
import org.junit.platform.testkit.engine.EventStatistics;

/**
 * Runs the classes in the demo package with the JUnit Platform Test Kit and checks the results shown in the article.
 */
class LifecycleResultsTest {

  private static final String PKG = "com.howtodoinjava.junit.beforeeach.demo.";

  @Test
  void exceptionInBeforeEachFailsTheTest() {
    EngineTestKit.engine("junit-jupiter")
        .selectors(selectClass(PKG + "FailingSetupDemo"))
        .execute()
        .testEvents()
        .assertStatistics(stats -> stats.started(1).failed(1).succeeded(0));
  }

  @Test
  void privateBeforeEachStillRunsWithWarning() {
    EngineTestKit.engine("junit-jupiter")
        .selectors(selectClass(PKG + "PrivateBeforeEachDemo"))
        .execute()
        .testEvents()
        .assertStatistics(stats -> stats.started(1).succeeded(1));

    assertTrue(issues("PrivateBeforeEachDemo").getFirst().message().contains("should not be private"));
  }

  @Test
  void staticBeforeEachIsADiscoveryError() {
    List<DiscoveryIssue> issues = issues("StaticBeforeEachDemo");
    assertEquals(DiscoveryIssue.Severity.ERROR, issues.getFirst().severity());
    assertTrue(issues.getFirst().message().contains("must not be static"));
  }

  @Test
  void nonStaticBeforeAllNeedsPerClass() {
    List<DiscoveryIssue> issues = issues("NonStaticBeforeAllDemo");
    assertEquals(DiscoveryIssue.Severity.ERROR, issues.getFirst().severity());
    assertTrue(issues.getFirst().message()
        .contains("must be static unless the test class is annotated with @TestInstance(Lifecycle.PER_CLASS)"));
  }

  @Test
  void repetitionInfoFailsForPlainTest() {
    EngineTestKit.engine("junit-jupiter")
        .selectors(selectClass(PKG + "RepetitionInfoMixDemo"))
        .execute()
        .testEvents()
        .assertStatistics(stats -> stats.started(3).succeeded(2).failed(1));
  }

  private static List<DiscoveryIssue> issues(String simpleName) {
    return EngineTestKit.engine("junit-jupiter")
        .selectors(selectClass(PKG + simpleName))
        .discover()
        .getDiscoveryIssues();
  }
}
