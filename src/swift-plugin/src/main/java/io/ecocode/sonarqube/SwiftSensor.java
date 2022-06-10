package io.ecocode.sonarqube;

import io.ecocode.antlr.ParseTreeAnalyzer;
import io.ecocode.antlr.SwiftAntlrContext;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;

public class SwiftSensor implements Sensor {

    private static final String SWIFT_KEY = "swift";

    @Override
    public void describe(SensorDescriptor sensorDescriptor) {
        sensorDescriptor
                .onlyOnLanguage(SWIFT_KEY)
                .name("ecoCode Swift Sensor")
                .onlyOnFileType(InputFile.Type.MAIN);
    }

    @Override
    public void execute(SensorContext sensorContext) {
        final SwiftAntlrContext antlrContext = new SwiftAntlrContext();
        // Analyse source files
        new ParseTreeAnalyzer(SWIFT_KEY, InputFile.Type.MAIN, antlrContext, sensorContext)
                .analyze(new EcoCodeVisitor());
    }
}
