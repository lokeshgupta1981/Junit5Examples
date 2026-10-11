Source code for the article https://howtodoinjava.com/junit/tags/

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven Surefire 3.6.0, Gradle 9.8.1 (wrapper).

Run with Maven:

    mvn test                                  # all 7 tests
    mvn test -Dgroups=fast                    # 3 tests
    mvn test -DexcludedGroups=slow            # 5 tests
    mvn test -Dgroups="recipes & !slow"       # 3 tests
    mvn test -Pquick                          # profile, fast tests only
    mvn test -Pci                             # profile, everything except slow
    mvn test -Dtest=NightlySuite              # the NightlySuite test suite
    mvn test -Dtest=BadTagCheck               # shows the warning for an invalid tag

Run with Gradle:

    ./gradlew test                            # slow tests excluded
    ./gradlew fastTest                        # fast tests only
    ./gradlew test -Ptags='recipes & !slow'
