package com.howtodoinjava.archtests;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static org.assertj.core.api.Assertions.assertThat;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.EvaluationResult;
import org.junit.jupiter.api.Test;

class ClassFileImporterTest {

  @Test
  void importAndCheckWithoutTheJUnitEngine() {
    JavaClasses imported = new ClassFileImporter()
        .withImportOption(new ImportOption.DoNotIncludeTests())
        .importPackages("com.howtodoinjava.recipes");

    JavaClass service = imported.get("com.howtodoinjava.recipes.service.RecipeService");

    ArchRule rule = classes()
        .that().resideInAPackage("..model..")
        .should().beRecords();

    EvaluationResult result = rule.evaluate(imported);

    System.out.println("imported classes: " + imported.size());
    System.out.println("service depends on: " + service.getDirectDependenciesFromSelf().stream()
        .map(d -> d.getTargetClass().getSimpleName())
        .filter(n -> n.startsWith("Recipe"))
        .distinct().sorted().toList());
    System.out.println("violations: " + result.hasViolation());

    assertThat(imported.size()).isEqualTo(5);
    assertThat(result.hasViolation()).isFalse();
    rule.check(imported);
  }
}
