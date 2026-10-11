Source code for the article https://howtodoinjava.com/junit/gradle-dependency/

Versions: Java 25, JUnit 6.1.3 (junit-bom), Gradle 9.8.1 (wrapper included).

Run the unit tests (tests tagged "slow" are excluded):

    ./gradlew test

Run the unit tests and the integrationTest suite (JVM Test Suite plugin):

    ./gradlew check

Run one test method:

    ./gradlew test --tests 'LoyaltyPointsTest.rejectsNegativeTotal'

Sub folders, each a separate Gradle build with its own wrapper:

- groovy-dsl: the same setup written in the Groovy DSL (build.gradle)
- vintage-example: JUnit 4 tests next to JUnit 6 tests with the JUnit Vintage engine
