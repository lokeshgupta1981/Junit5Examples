Source code for the article https://howtodoinjava.com/junit/junit-version-with-spring-boot/

A small recipe REST API tested three ways: a plain JUnit test, a @WebMvcTest slice with MockMvcTester and @MockitoBean, and a @SpringBootTest. The pom overrides the JUnit version that Spring Boot manages.

Versions: Java 25, Spring Boot 4.1.1 (spring-boot-starter-parent; it manages JUnit 6.0.3, overridden to 6.1.3 with the junit-jupiter.version property), Spring Framework 7.0.9, Maven 3.9 or later.

Run the tests:

    mvn test

Check the JUnit version in use:

    mvn dependency:tree -Dincludes=org.junit.jupiter:junit-jupiter,org.junit.platform:junit-platform-engine
