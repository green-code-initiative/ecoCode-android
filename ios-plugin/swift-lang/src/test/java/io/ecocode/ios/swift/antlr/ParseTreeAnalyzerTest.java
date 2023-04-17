/*
 * ecoCode iOS plugin - Help the earth, adopt this green plugin for your applications
 * Copyright © 2022 green-code-initiative (https://www.ecocode.io/)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.ecocode.ios.swift.antlr;

import io.ecocode.ios.swift.EcoCodeSwiftVisitor;
import io.ecocode.ios.swift.Swift;
import io.ecocode.ios.swift.TestHelper;
import org.junit.Test;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.internal.SensorContextTester;

import static org.assertj.core.api.Assertions.assertThat;
public class ParseTreeAnalyzerTest {

    @Test
    public void analyze() throws Throwable {
        final SwiftAntlrContext antlrContext = new SwiftAntlrContext();
        SensorContextTester sensorContext = TestHelper.testFile("checks/IdleTimerDisabled_trigger.swift");
        ParseTreeAnalyzer analyzer = new ParseTreeAnalyzer(Swift.KEY, InputFile.Type.MAIN, antlrContext, sensorContext);
        analyzer.analyze(new EcoCodeSwiftVisitor());

        assertThat(sensorContext.allIssues()).hasSize(1);
    }

}
