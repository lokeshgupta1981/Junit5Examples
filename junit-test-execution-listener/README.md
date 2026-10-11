Source code for the article https://howtodoinjava.com/junit/how-to-add-listner-in-junit-testcases/

Three ways to listen to test events: a JUnit 4 RunListener added to JUnitCore, a JUnit Platform TestExecutionListener registered through ServiceLoader (src/test/resources/META-INF/services), and a Jupiter TestWatcher extension.

Versions: Java 25, JUnit 6.1.3 (junit-bom, junit-jupiter, junit-platform-launcher), JUnit 4.13.2, maven-surefire-plugin 3.6.0, exec-maven-plugin 3.6.4, Maven 3.9 or later.

Run the tests with the listener and the watcher:

    mvn test

See the failure events (this class fails on purpose and is excluded by default):

    mvn test -Dtest=BookingFailingTest

Run the JUnit 4 class with the RunListener:

    mvn -q test-compile exec:java -Dexec.mainClass=com.howtodoinjava.junit.listener.RunJUnit4WithListener

Turn the ServiceLoader listener off for one run:

    mvn test -Djunit.platform.execution.listeners.deactivate=com.howtodoinjava.junit.listener.SlowTestListener
