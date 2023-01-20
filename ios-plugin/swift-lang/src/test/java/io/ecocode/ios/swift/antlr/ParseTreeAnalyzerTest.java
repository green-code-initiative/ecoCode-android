package io.ecocode.ios.swift.antlr;

import io.ecocode.ios.swift.Swift;
import io.ecocode.ios.swift.checks.CheckTestHelper;
import org.antlr.v4.runtime.Token;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.internal.SensorContextTester;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
public class ParseTreeAnalyzerTest {

    @Test
    public void analyze() throws Throwable {
        final SwiftAntlrContext antlrContext = new SwiftAntlrContext();
        SensorContextTester sensorContext = CheckTestHelper.analyzeTestFile("checks/IdleTimerDisabled_trigger.swift");
        ParseTreeAnalyzer analyzer = new ParseTreeAnalyzer(Swift.KEY, InputFile.Type.MAIN, antlrContext, sensorContext);

        assertThat(sensorContext.allIssues()).hasSize(1);
    }

}
