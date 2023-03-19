![Logo](docs/resources/5ekko.png)
======================================

Mobile apps running on top of battery-limited devices are more than others concerned by the reduction of their environmental footprint. Hence, we created `ecoCode mobile`, the version of ecoCode project fully dedicated to mobile platforms. It provides static code analyzers to highlight code structures that may have a negative ecological impact: energy over-consumption, "fatware", shortening devices' lifespan, etc.

ecoCode mobile is based on evolving catalogs of [best practices](https://github.com/cnumr/best-practices-mobile), for Android and iOS. A SonarQube plugin then implement these catalogs as rules for scanning your projects. To learn more, take a look at the [complete presentation](docs/resources/devfest-2022.pdf) (in french) or the [presentation in a nutshell](docs/resources/apidays-2022.pdf) (in english).

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

🌿 SonarQube Plugin
-------------------

2 mobile technologies are supported by the plugin right now:

- [x] [Android](android-plugin/)
- [x] [iOS](ios-plugin) <sub>NEW</sub>

![Screenshot](android-plugin/docs/screenshot.png)

<sub>The custom GUI above is reserved to educational purpose only.</sub>

🤝 Partners
------------

[![Snapp’](android-plugin/docs/logoSnapp.png)](https://www.snapp.fr)
[![Université de Pau](android-plugin/docs/logoUnivPau.png)](https://www.univ-pau.fr/)
[![Région Nouvelle-Aquitaine](android-plugin/docs/logoNA.png)](https://www.nouvelle-aquitaine.fr)
[![Solocal / PagesJaunes](android-plugin/docs/logoSolocal.png)](https://www.pagesjaunes.fr)
[![inside|app](ios-plugin/docs/logoInsideApp.jpg)](https://www.insideapp.fr/)

📢 Cite this work
------------------

If you use ecoCode in an academic work we would be really glad if you cite our seminal paper using the following bibtex entry:

```bibtex
@inproceedings{10.1145/3551349.3559518,
  author = {Le Goaer, Olivier and Hertout, Julien},
  title = {EcoCode: A SonarQube Plugin to Remove Energy Smells from Android Projects},
  year = {2023},
  isbn = {9781450394758},
  publisher = {Association for Computing Machinery},
  address = {New York, NY, USA},
  url = {https://doi.org/10.1145/3551349.3559518},
  doi = {10.1145/3551349.3559518},
  booktitle = {37th IEEE/ACM International Conference on Automated Software Engineering},
  articleno = {157},
  numpages = {4},
  keywords = {android, energy, smells, debt, quality, battery},
  location = {Rochester, MI, USA},
  series = {ASE22}
}
```

🛒 Distribution
---------------

Ready to use binaries are available [from GitHub](https://github.com/green-code-initiative/ecoCode-mobile/releases).

🧩 Plugins version compatibility
------------------

| Plugins Version | SonarQube version          |
|-----------------|----------------------------|
| 0.0.+           | SonarQube 8.9.+ LTS to 9.3 |
| 0.1.+           | SonarQube 9.4.+ LTS to 9.9 |
| 1.0.+           | SonarQube 9.4.+ LTS to 9.9 |

☕ Plugin Java part compatibility
------------------

| Plugins Version  | Java version |
|------------------|--------------|
| 0.0.+            | 11 / 17      |
| 0.1.+            | 11 / 17      |
| 1.0.+            | 11 / 17      |
