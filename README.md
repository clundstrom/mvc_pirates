# The Jolly Pirates 
--
This project simulates a registry adhering to the Model-view-controller pattern.

### Running the build

Unzip project.

from the command-line run:

```
java -jar mvc_pirates.jar
```

database.json will be generated in the same folder.

### Dependencies

* [GSON 2.8.5](https://github.com/google/gson)

* [Java 11.01](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)

### Compilation

To compile the project:
 
<strong>Gradle</strong>

In build.gradle add:

```json
dependencies {
    implementation "com.google.code.gson:gson:2.8.5"
}
```

<strong>Native java</strong>

Download dependency jar and add to project.

[GSON 2.8.5](https://github.com/google/gson)
