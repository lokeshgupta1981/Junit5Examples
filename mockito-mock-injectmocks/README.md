# Mockito @Mock vs @InjectMocks

Source code for the article [Mockito @Mock vs @InjectMocks: Difference With Examples](https://howtodoinjava.com/mockito/mockito-mock-injectmocks/).

## Versions

- Java 25
- JUnit Jupiter 6.1.3
- Mockito 5.24.0 (loaded as a Java agent by Surefire)
- Maven 3.9+

## What is inside

| Test class | Shows |
|---|---|
| RecipeServiceTest | @Mock repository + @InjectMocks service with MockitoExtension |
| InjectionStrategiesTest | Constructor (biggest constructor, null for a missing mock), setter and field injection, a record under test |
| SpyInjectionTest | @Spy injected into @InjectMocks, stubbing a spy with doReturn() |
| SpyOnInjectMocksTest | @Spy and @InjectMocks on the same field |
| OpenMocksTest | MockitoAnnotations.openMocks() without the extension |
| MockAndInjectMocksDemoTest | Fails on purpose: @Mock and @InjectMocks on one field are rejected |
| MissingExtensionDemoTest | Fails on purpose: NullPointerException without MockitoExtension |
| MixedInjectionDemoTest | Fails on purpose: a field skipped after constructor injection |
| FinalFieldDemoTest | Fails on purpose: a final field is not replaced by the mock |
| FailureDemosTest | Runs the four demo classes and checks their exact errors |

## Run

```bash
mvn test
```

The four demo classes are tagged `fails-on-purpose` and excluded by default. To see their errors:

```bash
mvn test -Dtest=*DemoTest -DexcludedGroups=none
```
