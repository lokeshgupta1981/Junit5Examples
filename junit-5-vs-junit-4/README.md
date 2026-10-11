Source code for the article https://howtodoinjava.com/junit/junit-5-vs-junit-4/

The same library loan tests written three times: with JUnit 4 (package junit4, run by the JUnit Vintage engine), with JUnit Jupiter (package jupiter) and as converted by the OpenRewrite recipe org.openrewrite.java.testing.junit5.JUnit4to5Migration (package migrated).

Versions: Java 25, JUnit 6.1.3 (junit-bom, junit-jupiter, junit-vintage-engine), JUnit 4.13.2, Maven 3.9 or later, maven-surefire-plugin 3.6.0.

Run all tests:

    mvn test

Migrate JUnit 4 tests with OpenRewrite (review the changes before committing):

    mvn org.openrewrite.maven:rewrite-maven-plugin:run -Drewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-testing-frameworks:RELEASE -Drewrite.activeRecipes=org.openrewrite.java.testing.junit5.JUnit4to5Migration
