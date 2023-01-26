/*
 * ecoCode iOS plugin - Help the earth, adopt this green plugin for your applications
 * Copyright © 2022 Green code Initiative (https://www.ecocode.io/)
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
package io.ecocode.ios.swift.checks;

import io.ecocode.ios.swift.EcoCodeSwiftVisitor;
import io.ecocode.ios.swift.Swift;
import io.ecocode.ios.swift.TestHelper;
import io.ecocode.ios.swift.antlr.ParseTreeAnalyzer;
import io.ecocode.ios.swift.antlr.SwiftAntlrContext;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.internal.SensorContextTester;

public class CheckTestHelper {


    public static SensorContextTester analyzeTestFile(String relativePath) {
        SensorContextTester context = TestHelper.testFile(relativePath);

        final SwiftAntlrContext antlrContext = new SwiftAntlrContext();
        new ParseTreeAnalyzer(Swift.KEY, InputFile.Type.MAIN, antlrContext, context)
                .analyze(new EcoCodeSwiftVisitor());

        return context;
    }
}
