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
package io.ecocode.ios.checks;

import io.ecocode.ios.antlr.AntlrContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.internal.TestInputFileBuilder;
import org.sonar.api.batch.sensor.internal.SensorContextTester;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class RuleCheckTest {

    private static final String TEST_ROOT = "src/test/resources";
    private static final int LINE_COUNT = 100;

    @Test
    public void fillContext() {

        AntlrContext antlrContext = new AntlrContext() {
            @Override
            public void loadFromStreams(InputFile inputFile, InputStream file, InputStream linesStream, Charset charset) throws IOException {
            }

            @Override
            public InputFile getFile() {
                return new TestInputFileBuilder("", "main.swift")
                        .setType(InputFile.Type.MAIN)
                        .setLines(LINE_COUNT)
                        .setOriginalLineEndOffsets(new int[LINE_COUNT])
                        .setOriginalLineStartOffsets(new int[LINE_COUNT])
                        .setModuleBaseDir(Paths.get(TEST_ROOT))
                        .setLanguage("swift").build();
            }

            @Override
            public int[] getLineAndColumn(int global) {
                return new int[] {1, 1};
            }
        };

        RuleCheck ruleCheck = new RuleCheck("rule1", "/rules/rules.json", "repositoryKey") {

            @Override
            public void apply(ParseTree tree) {
                this.recordIssue(this.ruleId, 0);
            }
        };


        ruleCheck.apply(null);

        SensorContextTester sensorContext  = SensorContextTester.create(new File(TEST_ROOT));
        sensorContext.fileSystem().add(antlrContext.getFile());
        ruleCheck.fillContext(sensorContext, antlrContext);

        assertThat(sensorContext.allIssues()).hasSize(1);

    }
}
