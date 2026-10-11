Source code for the article https://howtodoinjava.com/junit/how-to-execute-junit-testcases-with-junitcore/

Runs JUnit tests from Java code: JUnitCore for JUnit 4 tests, the JUnit Platform Launcher API (discovery request, session, SummaryGeneratingListener, tag and class name filters, fail fast with CancellationToken), and the Console Launcher.

Versions: Java 25, JUnit 6.1.3 (junit-bom, junit-jupiter, junit-platform-launcher, junit-vintage-engine), JUnit 4.13.2, maven-surefire-plugin 3.6.0, exec-maven-plugin 3.6.4, Maven 3.9 or later.

Run the tests:

    mvn test

Run one of the runner classes (they live in src/test/java because they need the test classpath):

    mvn -q test-compile exec:java -Dexec.mainClass=com.howtodoinjava.junit.runner.RunWithLauncher
    mvn -q test-compile exec:java -Dexec.mainClass=com.howtodoinjava.junit.runner.RunWithJUnitCore
    mvn -q test-compile exec:java -Dexec.mainClass=com.howtodoinjava.junit.runner.RunCheckoutTests
    mvn -q test-compile exec:java -Dexec.mainClass=com.howtodoinjava.junit.runner.RunFailFast

RefundFailingTest fails on purpose to show failure reports and is excluded from mvn test.
