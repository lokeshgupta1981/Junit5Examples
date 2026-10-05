Source code for the article https://howtodoinjava.com/?p=44108

# ArchUnit Tutorial: Architecture Tests with JUnit 6

A small Spring Boot recipe app plus ArchUnit tests for layers, naming, Spring annotations,
package cycles, onion architecture, general coding rules and frozen rules for legacy code.

## Versions

- Java 25
- Spring Boot 4.1.1
- JUnit 6.1.3
- ArchUnit 1.5.1 (artifact *com.tngtech.archunit:archunit-junit6*)
- Maven 3.9.x

## Project layout

- *com.howtodoinjava.recipes* - layered Spring Boot app (controller, service, repository, model). All rules pass.
- *com.howtodoinjava.mealplanner* - onion architecture sample (domain model, domain service, application, adapters).
- *com.howtodoinjava.legacy* - code that breaks the rules on purpose, used to show the failure messages and frozen rules.
- *archunit_store/* - the committed violation store of the frozen rule in *FrozenRulesTest*.

## Run

```bash
mvn test
```

Run one test class:

```bash
mvn test -Dtest=LegacyViolationsTest
```

*LegacyViolationsTest* and *FreezingBehaviorTest* print the ArchUnit failure messages to the console.
