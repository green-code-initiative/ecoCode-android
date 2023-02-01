package io.ecocode.java.checks.energy.sobriety;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class ThriftyMotionSensorRuleTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/sobriety/ThriftyMotionSensorCheck.java")
                .withCheck(new ThriftyMotionSensorRule())
                .verifyIssues();
    }
}
