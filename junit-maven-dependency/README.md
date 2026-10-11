Source code for the article https://howtodoinjava.com/junit/maven-dependency/

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven 3.9 or later, Maven Surefire and Failsafe 3.6.0.

Run the unit tests:

    mvn test

Run the unit tests and the integration test (ShippingCalculatorIT):

    mvn verify

Run one test class or one method:

    mvn test -Dtest=ShippingCalculatorTest
    mvn test -Dtest=ShippingCalculatorTest#rejectsZeroWeight

The folder vintage-example is a separate Maven project that runs JUnit 4 and JUnit 6 tests in one build:

    cd vintage-example
    mvn test
