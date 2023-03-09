/*
 * Sonar Groovy Plugin
 * Copyright (C) 2010-2021 SonarQube Community
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugins.groovy;

import org.junit.Test;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition.BuiltInActiveRule;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.utils.ValidationMessages;
import org.sonar.plugins.groovy.codenarc.CodeNarcRulesDefinition;
import org.sonar.plugins.groovy.foundation.Groovy;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class GroovySonarWayProfileTest {

    @Test
    public void shouldCreateSonarWayProfile() {
        ValidationMessages messages = ValidationMessages.create();

        GroovySonarWayProfile profileDef = new GroovySonarWayProfile();
        BuiltInQualityProfilesDefinition.Context profileContext = new BuiltInQualityProfilesDefinition.Context();
        profileDef.define(profileContext);
        BuiltInQualityProfilesDefinition.BuiltInQualityProfile profile = profileContext.profile(Groovy.KEY, Groovy.PROFILE_NAME);
        assertThat(profile.language()).isEqualTo(Groovy.KEY);
        List<BuiltInActiveRule> activeRules = profile.rules();

        // Here we check the rules.xml file to get the number of rules in it
        File profile_default = new File("src/main/resources/org/sonar/plugins/groovy/rules.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(profile_default);
            doc.getDocumentElement().normalize();
            NodeList rule = doc.getElementsByTagName("rule");
            assertThat(activeRules).as("Expected number of rules in profile").hasSize(rule.getLength());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        assertThat(profile.name()).isEqualTo(Groovy.PROFILE_NAME);

        // Check that we use severity from the read rule and not default one.
        assertThat(activeRules.get(0).overriddenSeverity()).isNull();
        assertThat(messages.hasErrors()).isFalse();

        // Check that all rules in "Sonar way" actually exist
        CodeNarcRulesDefinition definition = new CodeNarcRulesDefinition();
        RulesDefinition.Context rulesContext = new RulesDefinition.Context();
        definition.define(rulesContext);
        RulesDefinition.Repository repository =
                rulesContext.repository(CodeNarcRulesDefinition.REPOSITORY_KEY);

        Set<String> rules = new HashSet<>();
        for (RulesDefinition.Rule rule : repository.rules()) {
            rules.add(rule.key());
        }
        for (BuiltInActiveRule activeRule : profile.rules()) {
            assertThat(rules.contains(activeRule.ruleKey()))
                    .as("No such rule: " + activeRule.ruleKey())
                    .isTrue();
        }
    }
}
