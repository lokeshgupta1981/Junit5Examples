Source code for the article https://howtodoinjava.com/junit/xml-reports/

Versions: Java 25, JUnit 6.1.3 (junit-bom, junit-platform-reporting), Maven Surefire 3.6.0, Gradle 9.8.1 (wrapper included).

    mvn test

Reports written by the build:

- target/surefire-reports/TEST-*.xml: the legacy JUnit XML format written by Surefire (display names enabled)
- target/open-test-reports/open-test-report.xml: the Open Test Reporting XML format written by JUnit

The class demo/CardMaskingDemoTest fails on purpose and is excluded from the default build.
Run it to see a failure in both XML formats:

    mvn test -Dtest=CardMaskingDemoTest

Convert the Open Test Reporting XML to a single HTML file with the Open Test Reporting CLI 0.2.7:

    java -jar open-test-reporting-cli-0.2.7-standalone.jar html-report --output target/open-test-report.html target/open-test-reports/open-test-report.xml

Gradle: the same project builds with Gradle and writes both formats to build/test-results/test:

    ./gradlew test
