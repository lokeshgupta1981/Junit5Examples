Source code for the article https://howtodoinjava.com/?p=44236

# Mockito 5 Tutorial with JUnit 6

Mocks with *@Mock* and *@InjectMocks*, stubbing, *verify()*, argument matchers,
*ArgumentCaptor*, spies, *mockStatic()*, BDD style and the common Mockito errors,
tested with JUnit 6 on Java 25.

## Versions

- Java 25
- Mockito (mockito-core, mockito-junit-jupiter) 5.24.0
- JUnit 6.1.3 (junit-bom, JUnit Jupiter)
- Maven 3.9+ (maven-surefire-plugin 3.6.0, maven-dependency-plugin 3.11.0)

The *pom.xml* loads *mockito-core* as a Java agent in the Surefire *argLine*, so JDK 21 and
later print no "Mockito is currently self-attaching" warning.

## Run

```bash
mvn test
```

Run one test class:

```bash
mvn test -Dtest=StubbingTest
```

*CommonErrorsTest* runs small example tests that fail on purpose (with the JUnit Platform
testkit) and prints the real Mockito messages to the console, so the build stays green.

## Classes

| Class | Topic |
|---|---|
| LoanService | Class under test, uses *BookRepository* and *Notifier* |
| FeeCalculator | Final class, used for spies and final class mocking |
| LoanIds | Static method *next()*, used for *mockStatic()* |
| IntroTest | Quick reference |
| AnnotationsTest | *@Mock*, *@InjectMocks*, *MockitoExtension* |
| StubbingTest | Default values, *thenReturn()*, consecutive calls, *thenThrow()*, *doThrow()* |
| VerifyTest | *verify()*, *times()*, *never()*, *verifyNoMoreInteractions()* |
| MatchersTest | *anyString()*, *eq()*, *contains()*, *argThat()* |
| CaptorTest | *@Captor* and *ArgumentCaptor.forClass()* |
| SpyTest | Mock vs spy, *doReturn()* on a spy |
| StaticMockTest | *mockStatic()* with try-with-resources |
| BddStyleTest | *given()* / *then().should()* |
| CommonErrorsTest | *UnnecessaryStubbingException*, *PotentialStubbingProblem*, *lenient()*, final classes |
