/*
 * ecoCode iOS plugin - Help the earth, adopt this green plugin for your applications
 * Copyright © 2022 CNumR (https://www.ecocode.io/)
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
package io.ecocode.ios.swift;

import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.internal.DefaultInputFile;
import org.sonar.api.batch.fs.internal.TestInputFileBuilder;
import org.sonar.api.batch.sensor.internal.SensorContextTester;

import java.io.File;
import java.nio.file.Paths;

public class TestHelper {

    private static final String TEST_ROOT = "src/test/resources";
    private static final int LINE_COUNT = 100;

    public static SensorContextTester testFile(String relativePath) {
        SensorContextTester context = SensorContextTester.create(new File(TEST_ROOT));
        DefaultInputFile testFile = new TestInputFileBuilder("", relativePath)
                .setType(InputFile.Type.MAIN)
                .setLines(LINE_COUNT)
                .setOriginalLineEndOffsets(new int[LINE_COUNT])
                .setOriginalLineStartOffsets(new int[LINE_COUNT])
                .setModuleBaseDir(Paths.get(TEST_ROOT))
                .setLanguage(Swift.KEY).build();
        context.fileSystem().add(testFile);

        return context;
    }
}
