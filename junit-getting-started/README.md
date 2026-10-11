Source code for the article https://howtodoinjava.com/junit/getting-started-with-junit/

JUnit 6 getting started example: a shopping cart and its first JUnit Jupiter tests.

Versions: Java 25 (JUnit 6 needs Java 17+), JUnit 6.1.3 (junit-bom), Maven 3.9 or later, maven-surefire-plugin 3.6.0, maven-compiler-plugin 3.16.0.

Run all tests:

    mvn test

Run one test method:

    mvn test -Dtest=ShoppingCartTest#totalOfTwoItems

See a failing test report (this class is excluded from the default run):

    mvn test -Dtest=ShoppingCartFailingTest
