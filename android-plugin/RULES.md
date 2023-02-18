# Android-specific Rules

Android-specific rules rely on a multi-scope scanning, including Java source files, Xml files, Gradle files, and more generally the project structure.

### 🍃 Environment

The set of rules comes from the detailed [Energy Smells catalog](https://olegoaer.perso.univ-pau.fr/android-energy-smells/). 3/4 of them have been already implemented in the plugin. Table of unimplemented rules below:

| # | **Rule Name**      |     **Scanner**     |      **Observation**     |
|---|:----------------|:-------------:|:-------------:|
| ELEA001 | Everlasting Service        | Java | Requires `PostProjectAnalysisTask()` callback |
| EBOT004 | Uncached Data Reception       | Java | Requires `PostProjectAnalysisTask()` callback |
| ESOB003/ESOB004 | Dark UI      | Java, Xml | Partially implemented (e.g. [1](./src/main/java/io/ecocode/xml/checks/sobriety/DarkUIBrightColorsXmlRule.java), [2](./src/main/java/io/ecocode/xml/checks/sobriety/DarkUIThemeXmlRule.java)) |
| ESOB009 | Day Night Mode     | File System, Xml | Requires `PostProjectAnalysisTask()` callback |
| ESOB015 | Animation-free | Java, Xml, File System |  |
| EPOW008 | Constrainted Worker | Java |  |
| EBAT001 | Service@Boot-time    | Java, Xml  | Likely detectable in Xml only |
| EREL004 | Same dependencies    | Gradle | Requires a knowledge base |
| EREL005 | Duplicate dependencies    | Gradle | Requires a knowledge base |
| EREL007 | Clear cache    | Java | Method `deleteRecursively()` is Kotlin-only |
| EREL008 | Convert to WebP | File System |  |
| EREL009 | Shrink Resources    | Gradle |  |
| EREL010 | Disable Obfuscation    | Gradle |  |


### 🤝 Social

The set of rules comes from the detailed [Social Smells catalog](https://olegoaer.perso.univ-pau.fr/android-social-smells/index.html) (work in progress). 1 rule has been implemented so far in the plugin. Table of unimplemented rules below:

| # | **Rule Name**      |     **Scanner**     |      **Observation**     |
| ---|:----------------|:-------------:|:-------------:|
| SPRI001 | Crashlytics automatic opt-in       | Java, Xml |  |
| SPRI003 | Hidden Tracker Risk      | Gradle | Threshold to be decided |
| SPRI004 | Tracking Id      | Java |  |
| SPRI005 | Explain Permission     | Java |  |
| SGDP001 | Google consent | Java | "Good smell" syndrome here |
| SINC001 | Aging devices   | Gradle  |  |
