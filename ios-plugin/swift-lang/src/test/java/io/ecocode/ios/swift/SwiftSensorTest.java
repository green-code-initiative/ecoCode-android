package io.ecocode.ios.swift;

import org.junit.Test;
import org.sonar.api.batch.sensor.internal.SensorContextTester;

import static org.assertj.core.api.Assertions.assertThat;

public class SwiftSensorTest {

    @Test
    public void execute() {
        SensorContextTester sensorContext = TestHelper.testFile("checks/IdleTimerDisabled_trigger.swift");
        SwiftSensor sensor = new SwiftSensor();
        sensor.execute(sensorContext);

        assertThat(sensorContext.allIssues()).hasSize(1);
    }
}
