package com.howtodoinjava.archtests;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(packages = "com.howtodoinjava.recipes",
    importOptions = ImportOption.DoNotIncludeTests.class)
class RecipeArchitectureTest {

  // 1. Layers
  @ArchTest
  static final ArchRule layersAreRespected = layeredArchitecture()
      .consideringAllDependencies()
      .layer("Controller").definedBy("..controller..")
      .layer("Service").definedBy("..service..")
      .layer("Repository").definedBy("..repository..")
      .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
      .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
      .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");

  // 2. Controllers never call repositories
  @ArchTest
  static final ArchRule controllersDoNotUseRepositories = noClasses()
      .that().resideInAPackage("..controller..")
      .should().dependOnClassesThat().resideInAPackage("..repository..")
      .because("controllers must go through the service layer");

  // 3. Spring stereotypes live in the right package
  @ArchTest
  static final ArchRule servicesLiveInServicePackage = classes()
      .that().areAnnotatedWith(Service.class)
      .should().resideInAPackage("..service..");

  @ArchTest
  static final ArchRule repositoriesLiveInRepositoryPackage = classes()
      .that().areAnnotatedWith(Repository.class)
      .should().resideInAPackage("..repository..");

  // 4. Naming
  @ArchTest
  static final ArchRule controllersAreNamedController = classes()
      .that().areAnnotatedWith(RestController.class)
      .should().haveSimpleNameEndingWith("Controller")
      .andShould().resideInAPackage("..controller..");

  // 5. No cycles between the top-level packages
  @ArchTest
  static final ArchRule noCycles = slices()
      .matching("com.howtodoinjava.recipes.(*)..")
      .should().beFreeOfCycles();
}
