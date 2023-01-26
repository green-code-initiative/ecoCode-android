### Android Plugin

The objective of this work-package is to develop rules that analyze the ecological impact of the source code of an **Android** mobile application.

- **Android Code** Challenge: Develop Android specific rules based on the work already provided by ecoCode.
- **Android Test** Challenge: This challenge is oriented around reflection to find ways to scientifically measure the verification of ecodesign rules.

If you are a mobile developer, you can contribute to our [Android plugin](../sonarqube-plugin-greenit/android-plugin/). Android-specific rules relies on a multi-scope scanning, including Java source files, Xml files and Gradle files.

Before submitting an Android-specific SonarQube custom rule, please take a look at our [naming conventions](https://doc.rules.ecocode.io/#how-to-specify-rules). Idealy, take also a look at the [helpers fonctions](./android-plugin/src/main/java/io/ecocode/java/checks/helpers) we wrote to avoid doing things the hard way.

#### Environment

The set of rules comes from the detailed [Energy Smells catalog](https://olegoaer.perso.univ-pau.fr/android-energy-smells/). 3/4 of them have been already implemented in the plugin. Table of unimplemented rules below:

| # | **Rule Name**      |     **Scanner**     |      **Observation**     |
|---|:----------------|:-------------:|:-------------:|
| ELEA001 | Everlasting Service        | Java | Requires `PostProjectAnalysisTask()` callback |
| EBOT004 | Uncached Data Reception       | Java | Requires `PostProjectAnalysisTask()` callback |
| ESOB003/ESOB004 | Dark UI      | Java, Xml | Partially implemented |
| ESOB009 | Day Night Mode     | File System, Xml | Requires `PostProjectAnalysisTask()` callback |
| ESOB015 | Animation-free | Java, Xml, File System |  |
| EPOW008 | Constrainted Worker | Java |  |
| EBAT001 | Service@Boot-time    | Java, Xml  | Can be detected in pure Xml |
| EREL004 | Same dependencies    | Gradle |  |
| EREL005 | Duplicate dependencies    | Gradle |  |
| EREL007 | Clear cache    | Java | Method `deleteRecursively()` is Kotlin-specific |
| EREL008 | Convert to WebP | File System |  |
| EREL009 | Shrink Resources    | Gradle |  |
| EREL010 | Disable Obfuscation    | Gradle |  |


#### Social

The set of rules comes from the detailed [Social Smells catalog](https://olegoaer.perso.univ-pau.fr/android-social-smells/index.html) (work in progress). 0 rules have been implemented so far in the plugin. Table of unimplemented rules below:

| # | **Rule Name**      |     **Scanner**     |      **Observation**     |
| ---|:----------------|:-------------:|:-------------:|
| SPRI001 | Crashlytics automatic opt-in       | Java, Xml |  |
| SPRI002 | Google Tracker | Java |  |
| SPRI003 | Hidden Tracker Risk      | Gradle |  |
| SPRI004 | Tracking Id      | Java |  |
| SPRI005 | Explain Permission     | Java |  |
| SGDP001 | Google consent | Java |  |
| SINC001 | Aging devices   | Gradle  |  |
