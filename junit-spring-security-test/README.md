Source code for the article https://howtodoinjava.com/junit/how-to-unit-test-spring-security-authentication-with-junit/

Testing Spring Security authentication and authorization with JUnit: an expense API secured with HTTP Basic, URL rules and @PreAuthorize, tested with @WithMockUser, @WithUserDetails, SecurityMockMvcRequestPostProcessors (httpBasic(), user(), csrf()), MockMvcTester and a SecurityContext set by hand.

Versions: Java 25, Spring Boot 4.1.1 (Spring Security 7.1.1, Spring Framework 7.0.9), JUnit 6.1.3 (junit-jupiter.version override; Boot 4.1.1 manages 6.0.3), Maven 3.9 or later.

Users of the demo app: lokesh / password1 (USER), admin / password2 (USER, ADMIN).

Run the tests:

    mvn test
