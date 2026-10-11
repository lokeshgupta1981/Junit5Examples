Source code for the article https://howtodoinjava.com/junit/jacoco-test-coverage/

Versions: Java 25, JUnit 6.1.3 (junit-bom), JaCoCo 0.8.15, Maven Surefire 3.6.0, Gradle 9.8.1 (wrapper included).

Maven: run the tests, write the report to target/site/jacoco/index.html and check the coverage rules:

    mvn clean verify

See the coverage check fail with only one test class:

    mvn clean verify -Dtest=InvoiceCalculatorTest

Gradle: the same project builds with Gradle. The report is written to build/reports/jacoco/test/html/index.html:

    ./gradlew check
