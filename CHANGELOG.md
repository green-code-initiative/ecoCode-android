# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- Home page URL for plugins (for display on SonarQube administration page)
- add `eco-design` tag on all rules on Java, PHP, Python and javascript plugins

### Changed

- rename repository `ecoCode-mobile` to `ecoCode-android`

### Deleted

- Moved iOS plugin to its [own repository](https://github.com/green-code-initiative/ecoCode-ios).

## [1.0.1] - 2023-03-10

### Changed

- Fix plugin iOS release

## [1.0.0] - 2023-03-10

### Added

- [#5](https://github.com/green-code-initiative/ecocode-android/pull/5) Add IOS plugin delivery
- Add `CONTRIBUTING.md`
- Add `CODE_STYLE.md`
- move `INSTALL.md` to common doc in `ecoCode-common` repository

### Changed

- upgrade SonarQube version to 9.9
- [#27](https://github.com/green-code-initiative/ecocode-android/pull/27) preparation for Plugin SonarSource Marketplace integration
- docker / docker-compose upgrade
- update documentation
- upgrade RULES
- [#28](https://github.com/green-code-initiative/ecocode-android/pull/28) Add minSdk and targetSdk
- [#4](https://github.com/green-code-initiative/ecocode-android/pull/4) minSdk and targetSdk properties must be supported too
- [#23](https://github.com/green-code-initiative/ecocode-android/issues/23) Fix CodeNarc issue
- [#20](https://github.com/green-code-initiative/ecocode-android/issues/20) Refactoring `cnumr` to `greencodeinitiative`

## [0.1.0] - 2023-01-03

### Added

- [#13](https://github.com/green-code-initiative/ecocode-android/pull/13) Upgrade some versions + sonar version from 9.3
  to 9.8
- [#14](https://github.com/green-code-initiative/ecocode-android/issues/14) Improve release system

### Changed

- documentation upgrade (internal)
- optimization/refactoring on pom.xml dependencies (internal)
- Fix [WARNING] Maven-shade-plugin overlapping classes and upgrade SonarRuntime.

## [0.0.1] - 2022-12-15

### Added

- First official release of ecocode plugins : android plugin

[unreleased]: https://github.com/green-code-initiative/ecoCode/compare/v1.0.1...HEAD

[1.0.1]: https://github.com/green-code-initiative/ecoCode/releases/tag/v1.0.1

[1.0.0]: https://github.com/green-code-initiative/ecoCode/releases/tag/v1.0.0

[0.1.0]: https://github.com/green-code-initiative/ecoCode/releases/tag/v0.1.0

[0.0.1]: https://github.com/green-code-initiative/ecoCode/releases/tag/v0.0.1
