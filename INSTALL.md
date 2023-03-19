- [Common installation notes / requirements](#common-installation-notes--requirements)
- [Special points for Mobile plugins](#special-points-for-mobile-plugins)
  - [Project structure](#project-structure)
  - [Howto build the SonarQube ecoCode plugins](#howto-build-the-sonarqube-ecocode-plugins)
    - [Preliminary steps (only Android)](#preliminary-steps-only-android)
    - [Others steps](#others-steps)

Common installation notes / requirements
========================================

Please read common [INSTALL.md](https://github.com/green-code-initiative/ecoCode-common/blob/main/doc/INSTALL.md) in `ecoCode-common` repository.

Special points for Mobile plugins
=================================

Project structure
-----------------

Here is a preview of project tree :

```txt
ecoCode-mobile          # Root directory
|
+--android-plugin       # Android
|
+--codenarc-converter   # codenarc-converter
|
+--ios-plugin           # IOS
|
\--docker-compose.yml   # Docker compose file
```

You will find more information about the plugins’ architecture in their folders

Howto build the SonarQube ecoCode plugins
-----------------------------------------

### Preliminary steps (only Android)

The Android plugin uses [CodeNarc](https://codenarc.org/) to scan the gradle files of Android projects. To have more information about CodeNarc: [CodeNarc](/codenarc-converter/CodeNarc/README.md).

CodeNarc must be built separately.
Please build CodeNarc (Gradle 6.9.2, Java 11), then add this custom-built CodeNarc to Maven dependencies :

```sh
# Unix or MacOS
./tool_prepare-codenarc

# Windows
tool_prepare-codenarc.bat
```

### Others steps

please check above common installation notes.
