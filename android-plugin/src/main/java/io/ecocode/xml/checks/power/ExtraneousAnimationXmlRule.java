/*
 * ecoCode Android plugin - Provides rules to reduce the environmental footprint of your Android applications
 * Copyright © 2020 Green Code Initiative (contact@ecocode.io)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.ecocode.xml.checks.power;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.XmlTextRange;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheck;

import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * Checks the use extraneous animations.
 */
@Rule(key = "EC550", name = "Avoid extraneous animation", priority = Priority.MINOR)
@DeprecatedRuleKey(repositoryKey = "ecoCode-java", ruleKey = "ESOB015")
public class ExtraneousAnimationXmlRule extends SonarXmlCheck {
    private static final String ERROR_MESSAGE = "Avoiding extraneous animations in the UI is a good practice for saving battery power.";


    @Override
    public void scanFile(XmlFile xmlFile) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/res/animator/*.xml");
        URI uri = xmlFile.getInputFile().uri();
        Path xmlFilePath = Paths.get(uri);
        if (pathMatcher.matches(xmlFilePath)) {
            XmlTextRange textRange = new XmlTextRange(1, 1, 1, 2);
            this.reportIssue(textRange, ERROR_MESSAGE, Collections.emptyList());
        }
    }
}