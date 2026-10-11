package com.howtodoinjava.junit.assumptions;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.testkit.engine.EventConditions.abortedWithReason;
import static org.junit.platform.testkit.engine.EventConditions.event;
import static org.junit.platform.testkit.engine.EventConditions.test;
import static org.junit.platform.testkit.engine.TestExecutionResultConditions.instanceOf;
import static org.junit.platform.testkit.engine.TestExecutionResultConditions.message;

import org.junit.jupiter.api.Test;
import org.junit.platform.testkit.engine.EngineTestKit;
import org.junit.platform.testkit.engine.Events;
import org.opentest4j.TestAbortedException;

/**
 * Runs the example classes with the JUnit Platform Test Kit and checks the results the article shows.
 * Expects a local run: no CI, test.db.url or PAYMENT_SANDBOX_URL set, Linux, Java 25.
 */
class AssumptionResultsTest {

  private Events containers(Class<?> testClass) {
    return EngineTestKit.engine("junit-jupiter").selectors(selectClass(testClass)).execute().containerEvents();
  }

  private Events tests(Class<?> testClass) {
    return EngineTestKit.engine("junit-jupiter").selectors(selectClass(testClass)).execute().testEvents();
  }

  @Test
  void ciOnlyTestIsAborted() {
    tests(CiOnlyTest.class).assertStatistics(s -> s.started(1).succeeded(0).aborted(1).failed(0));
    tests(CiOnlyTest.class).assertThatEvents().haveExactly(1,
        event(test("publishesReportOnCi"),
            abortedWithReason(instanceOf(TestAbortedException.class), message("Assumption failed: Not running on CI"))));
  }

  @Test
  void assumeTrueResults() {
    tests(AssumeTrueTest.class).assertStatistics(s -> s.started(3).succeeded(2).aborted(1).failed(0));
  }

  @Test
  void assumeFalseResults() {
    tests(AssumeFalseTest.class).assertStatistics(s -> s.started(2).succeeded(2).aborted(0).failed(0));
  }

  @Test
  void assumingThatNeverAborts() {
    tests(AssumingThatTest.class).assertStatistics(s -> s.started(1).succeeded(1).aborted(0).failed(0));
  }

  @Test
  void abortResults() {
    tests(AbortTest.class).assertStatistics(s -> s.started(2).succeeded(1).aborted(1).failed(0));
    tests(AbortTest.class).assertThatEvents().haveExactly(1,
        event(test("callsPaymentSandbox"), abortedWithReason(message("PAYMENT_SANDBOX_URL is not set"))));
  }

  @Test
  void assumptionAbortsAssertionFails() {
    tests(AssumptionVsAssertionDemoTest.class)
        .assertStatistics(s -> s.started(2).succeeded(0).aborted(1).failed(1));
  }

  @Test
  void beforeAllAbortsWholeClass() {
    tests(BeforeAllAssumptionTest.class).assertStatistics(s -> s.started(0).aborted(0));
    containers(BeforeAllAssumptionTest.class).assertStatistics(s -> s.aborted(1));
  }

  @Test
  void beforeEachAbortsEachTest() {
    tests(BeforeEachAssumptionTest.class).assertStatistics(s -> s.started(2).succeeded(0).aborted(2));
  }

  @Test
  void annotationsSkipBeforeTheTestStarts() {
    tests(ConditionalAnnotationsTest.class).assertStatistics(s -> s.skipped(3).started(1).succeeded(1));
  }

  @Test
  void parameterizedAbortsOneInvocation() {
    tests(ParameterizedAssumptionTest.class).assertStatistics(s -> s.started(3).succeeded(2).aborted(1).failed(0));
  }
}
