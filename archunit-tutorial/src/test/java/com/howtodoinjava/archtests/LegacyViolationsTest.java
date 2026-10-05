package com.howtodoinjava.archtests;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

/**
 * The legacy package breaks the rules on purpose. Each test checks that the rule fails
 * and prints the failure message that ArchUnit reports.
 */
class LegacyViolationsTest {

  static JavaClasses legacy;

  @BeforeAll
  static void importLegacy() {
    legacy = new ClassFileImporter().importPackages("com.howtodoinjava.legacy");
  }

  static void expectViolation(ArchRule rule, String expected) {
    assertThatThrownBy(() -> rule.check(legacy))
        .isInstanceOf(AssertionError.class)
        .hasMessageContaining(expected)
        .satisfies(e -> System.out.println(e.getMessage() + "\n"));
  }

  @Test
  void controllerCallsRepository() {
    ArchRule rule = noClasses()
        .that().resideInAPackage("..controller..")
        .should().dependOnClassesThat().resideInAPackage("..repository..")
        .because("controllers must go through the service layer");
    expectViolation(rule, "ShoppingListController");
  }

  @Test
  void layerViolation() {
    ArchRule rule = layeredArchitecture()
        .consideringAllDependencies()
        .layer("Controller").definedBy("..controller..")
        .layer("Service").definedBy("..service..")
        .layer("Repository").definedBy("..repository..")
        .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");
    expectViolation(rule, "ShoppingListController");
  }

  @Test
  void serviceInWrongPackage() {
    ArchRule rule = classes()
        .that().areAnnotatedWith(Service.class)
        .should().resideInAPackage("..service..");
    expectViolation(rule, "DiscountHelper");
  }

  @Test
  void fieldInjection() {
    expectViolation(NO_CLASSES_SHOULD_USE_FIELD_INJECTION, "repository");
  }

  @Test
  void standardStreams() {
    expectViolation(NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS, "ReportPrinter");
  }

  @Test
  void packageCycle() {
    ArchRule rule = slices()
        .matching("com.howtodoinjava.legacy.(*)..")
        .should().beFreeOfCycles();
    expectViolation(rule, "Cycle detected");
  }
}
