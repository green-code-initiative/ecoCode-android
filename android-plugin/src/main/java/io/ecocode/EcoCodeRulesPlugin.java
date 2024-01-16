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
package io.ecocode;

import io.ecocode.java.JavaEcoCodeProfile;
import io.ecocode.java.JavaFileCheckRegistrar;
import io.ecocode.java.JavaRulesDefinition;
import io.ecocode.xml.XmlEcoCodeProfile;
import io.ecocode.xml.XmlRulesDefinition;
import io.ecocode.xml.XmlSensor;
import org.sonar.api.Plugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;
import org.sonar.plugins.groovy.GroovySensor;
import org.sonar.plugins.groovy.cobertura.CoberturaSensor;
import org.sonar.plugins.groovy.codenarc.CodeNarcSensor;
import org.sonar.plugins.groovy.foundation.Groovy;
import org.sonar.plugins.groovy.jacoco.JaCoCoExtensions;
import org.sonar.plugins.groovy.surefire.GroovySurefireSensor;

/**
 * Entry point of your plugin containing your custom rules
 */
public class EcoCodeRulesPlugin implements Plugin {

  public static final String FILE_SUFFIXES_KEY = "ecocode.xml.file.suffixes";

  @Override
  public void define(Context context) {

    // ===Add Java rules extension ===
    // server extensions -> objects are instantiated during server startup
    context.addExtension(JavaRulesDefinition.class);
    // batch extensions -> objects are instantiated during code analysis
    context.addExtension(JavaFileCheckRegistrar.class);
    // === Add Java rules profile ===
    context.addExtension(JavaEcoCodeProfile.class);
    // === Add XML rules extension ===
    context.addExtensions(
            PropertyDefinition.builder(FILE_SUFFIXES_KEY)
                    .name("File suffixes")
                    .description("Comma-separated list of suffixes for files to analyze.")
                    .defaultValue(".xml,.xsd,.xsl")
                    .multiValues(true)
                    .category("XML")
                    .onQualifiers(Qualifiers.PROJECT)
                    .build(),
            XmlRulesDefinition.class,
            XmlEcoCodeProfile.class,
            XmlSensor.class);
    // === Add Groovy rules extension ===
    context.addExtensions(Groovy.getExtensions())
            .addExtensions(GroovySensor.getExtensions())
            .addExtensions(CodeNarcSensor.getExtensions());
  }

}
