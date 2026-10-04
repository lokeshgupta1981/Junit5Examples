package com.howtodoinjava.recipes;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.testkit.engine.EventConditions.event;
import static org.junit.platform.testkit.engine.EventConditions.finishedWithFailure;
import static org.junit.platform.testkit.engine.TestExecutionResultConditions.instanceOf;
import static org.junit.platform.testkit.engine.TestExecutionResultConditions.message;

import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import org.junit.platform.testkit.engine.EngineTestKit;
import org.mockito.exceptions.base.MockitoException;

/** Runs the fails-on-purpose demo classes and checks the exact errors shown in the article. */
class FailureDemosTest {

  private static void assertFails(Class<?> demo, Class<? extends Throwable> type,
      Predicate<String> text) {
    EngineTestKit.engine("junit-jupiter")
        .configurationParameter("junit.jupiter.tags.exclude", "none")
        .selectors(selectClass(demo))
        .execute()
        .testEvents()
        .assertThatEvents()
        .haveExactly(1, event(finishedWithFailure(instanceOf(type), message(text))));
  }

  @Test
  void missingExtensionGivesNullPointerException() {
    assertFails(MissingExtensionDemoTest.class, NullPointerException.class,
        m -> m.equals("Cannot invoke \"com.howtodoinjava.recipes.RecipeRepository.findByName(String)\" "
            + "because \"this.recipeRepository\" is null"));
  }

  @Test
  void skippedFieldGivesNullPointerException() {
    assertFails(MixedInjectionDemoTest.class, NullPointerException.class,
        m -> m.equals("Cannot invoke \"com.howtodoinjava.recipes.TimeFormatter.format(int)\" "
            + "because \"this.timeFormatter\" is null"));
  }

  @Test
  void finalFieldIgnoresTheMock() {
    assertFails(FinalFieldDemoTest.class, RecipeNotFoundException.class,
        m -> m.equals("Recipe pancakes not found"));
  }

  @Test
  void mockAndInjectMocksOnOneFieldIsRejected() {
    assertFails(MockAndInjectMocksDemoTest.class, MockitoException.class,
        m -> m.contains("This combination of annotations is not permitted on a single field:\n"
            + "@Mock and @InjectMocks"));
  }
}
