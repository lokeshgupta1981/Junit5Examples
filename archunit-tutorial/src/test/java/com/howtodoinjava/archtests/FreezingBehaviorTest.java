package com.howtodoinjava.archtests;

import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.howtodoinjava.legacy.report.AuditPrinter;
import com.howtodoinjava.legacy.report.ReportPrinter;
import com.howtodoinjava.legacy.repository.ShoppingListRepository;
import com.tngtech.archunit.ArchConfiguration;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.freeze.FreezingArchRule;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Shows the two runs of a frozen rule with a fresh store in a temp folder.
 * Run 1 records the existing violation, run 2 fails only for the new class,
 * run 3 shows that fixed violations are removed from the store.
 */
class FreezingBehaviorTest {

  @TempDir
  Path store;

  @AfterEach
  void resetConfiguration() {
    ArchConfiguration.get().reset();
  }

  @Test
  void frozenRuleFailsOnlyForNewViolations() throws IOException {
    ArchConfiguration config = ArchConfiguration.get();
    config.setProperty("freeze.store.default.path", store.toString());
    config.setProperty("freeze.store.default.allowStoreCreation", "true");

    ArchRule rule = FreezingArchRule.freeze(NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS);

    // 1. First run: ReportPrinter already prints to System.out, the store records it
    JavaClasses before = new ClassFileImporter().importClasses(ReportPrinter.class);
    rule.check(before);

    // 2. Second run: AuditPrinter is new code that prints to System.err
    JavaClasses after = new ClassFileImporter()
        .importClasses(ReportPrinter.class, AuditPrinter.class);

    assertThatThrownBy(() -> rule.check(after))
        .isInstanceOf(AssertionError.class)
        .hasMessageContaining("AuditPrinter")
        .hasMessageNotContaining("ReportPrinter")
        .satisfies(e -> System.out.println(e.getMessage()));

    // 3. Third run: both printers are fixed, the store drops the old violation
    JavaClasses fixed = new ClassFileImporter().importClasses(ShoppingListRepository.class);
    rule.check(fixed);

    List<String> stored = storedViolations();
    System.out.println("stored violations after the fix: " + stored);
    assertThat(stored).isEmpty();
  }

  private List<String> storedViolations() throws IOException {
    try (Stream<Path> files = Files.list(store)) {
      Path violationFile = files
          .filter(f -> !f.getFileName().toString().equals("stored.rules"))
          .findFirst()
          .orElseThrow();
      return Files.readAllLines(violationFile).stream().filter(l -> !l.isBlank()).toList();
    }
  }
}
