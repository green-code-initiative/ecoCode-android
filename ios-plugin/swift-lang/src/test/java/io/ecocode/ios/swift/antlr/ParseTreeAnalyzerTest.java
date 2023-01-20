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
