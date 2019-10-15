# The Jolly Pirates 

This project simulates a registry adhering to the Model-view-controller pattern.

By Christoffer Lundström, Marcus Johansson and Arbnor Qavolli

### Running the build

Unzip project.

1. CD into mvc_pirates_jar directory. A file called mvc_pirates.jar should be located there.
2. From the command-line run:

```
java -jar mvc_pirates.jar
```

database.json will be generated in the same folder.


### Compilation

##### Dependencies

* [GSON 2.8.5](https://github.com/google/gson)

* [Java 11.01](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)

To compile the project:
 
<strong>Gradle</strong>

In build.gradle add:

```
dependencies {
    implementation "com.google.code.gson:gson:2.8.5"
}
```

<strong>Native java</strong>

Download dependency jar and add to project.

[GSON 2.8.5](https://github.com/google/gson)
