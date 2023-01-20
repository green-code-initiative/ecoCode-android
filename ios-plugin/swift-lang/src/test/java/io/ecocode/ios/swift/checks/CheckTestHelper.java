package io.ecocode.ios.swift.checks;

import io.ecocode.ios.swift.EcoCodeSwiftVisitor;
import io.ecocode.ios.swift.Swift;
import io.ecocode.ios.swift.TestHelper;
import io.ecocode.ios.swift.antlr.ParseTreeAnalyzer;
import io.ecocode.ios.swift.antlr.SwiftAntlrContext;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.internal.DefaultInputFile;
import org.sonar.api.batch.fs.internal.TestInputFileBuilder;
import org.sonar.api.batch.sensor.internal.SensorContextTester;

import java.io.File;
import java.nio.file.Paths;

public class CheckTestHelper {


    public static SensorContextTester analyzeTestFile(String relativePath) {
        SensorContextTester context = TestHelper.testFile(relativePath);

        final SwiftAntlrContext antlrContext = new SwiftAntlrContext();
        new ParseTreeAnalyzer(Swift.KEY, InputFile.Type.MAIN, antlrContext, context)
                .analyze(new EcoCodeSwiftVisitor());

        return context;
    }
}
