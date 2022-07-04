# ecoCode Swift SonarQube plugin

ecoCode Swift SonarQube plugin is an "eco-responsibility" static code analyzer for native iOS/macOS projects written in Swift. Its aim is to detect code smells to indicate weither the source code can be improved to reduce their environmental and social impact.

## Requirements

- A recent Java JDK version
- Maven 3.8 or later
- A local SonarQube instance for local testing

## Main commands

#### Local plugin deployment

In order to start the plugin, SONARQUBE_HOME environment variable must be set

If variable is already set, use:
```bash
$ mvn install
```

If variable is not set, it can be set inline:
```bash
$ SONARQUBE_HOME=~/path/to/sonarqube mvn install
```

When started locally SonarQube UI is available at http://localhost:9000

#### Packaging

```bash
$ mvn package
```
