Source code for the article https://howtodoinjava.com/?p=44109

# AssertJ Tutorial with JUnit 6

Fluent AssertJ assertions for strings, numbers, collections, maps, *Optional*, exceptions,
records, *java.time* values, soft assertions, custom assertions and conditions, plus
*MockMvcTester* in a Spring Boot *@WebMvcTest*.

## Versions

- Java 25
- AssertJ (assertj-core) 3.27.7
- JUnit 6.1.3 (JUnit Jupiter)
- Spring Boot 4.1.1 (Spring Framework 7.0.9)
- Maven 3.9+

## Run

```bash
mvn test
```

Run one test class:

```bash
mvn test -Dtest=CollectionAssertionsTest
```

Several tests print the AssertJ failure messages to the console (they catch the
*AssertionError* with *catchThrowable()* and assert on it), so the build stays green.

## Test classes

| Class | Topic |
|---|---|
| IntroTest | Quick reference |
| FailureMessagesTest | JUnit *assertEquals()* / *assertTrue()* vs AssertJ failure messages |
| StringAssertionsTest | String assertions |
| NumberAssertionsTest | Numbers, *isCloseTo()*, *within()*, *byLessThan()*, *withinPercentage()* |
| CollectionAssertionsTest | *contains*, *containsExactly*, *extracting*, *filteredOn*, *tuple* |
| MapAssertionsTest | Map assertions |
| OptionalAssertionsTest | *Optional* assertions |
| ExceptionAssertionsTest | *assertThatThrownBy*, *assertThatExceptionOfType*, *catchThrowable* |
| RecursiveComparisonTest | *usingRecursiveComparison()*, *ignoringFields()* |
| DateAssertionsTest | *LocalDate*, *LocalDateTime*, *Instant*, *Duration* |
| SoftAssertionsTest | *SoftAssertions.assertSoftly()* |
| SoftAssertionsExtensionTest | *SoftAssertionsExtension* and *@InjectSoftAssertions* |
| CustomAssertionsTest | *RecipeAssert* and *Condition* |
| CommonMistakesTest | Missing assertion, *size()* vs *hasSize()*, late *as()* |
| RecipeControllerTest | *@WebMvcTest* with *MockMvcTester* |
