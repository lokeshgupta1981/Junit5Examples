package com.howtodoinjava.archtests;

import static org.assertj.core.api.Assertions.assertThat;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class CustomImportOptionTest {

  @Test
  void skipLegacyPackageByLocation() {
    ImportOption skipLegacy = location -> !location.contains("/legacy/");

    JavaClasses imported = new ClassFileImporter()
        .withImportOption(new ImportOption.DoNotIncludeTests())
        .withImportOption(skipLegacy)
        .importPackages("com.howtodoinjava");

    boolean hasLegacy = imported.containPackage("com.howtodoinjava.legacy");
    boolean hasRecipes = imported.containPackage("com.howtodoinjava.recipes");
    System.out.println("legacy imported: " + hasLegacy + ", recipes imported: " + hasRecipes);

    assertThat(hasLegacy).isFalse();
    assertThat(hasRecipes).isTrue();
  }
}
