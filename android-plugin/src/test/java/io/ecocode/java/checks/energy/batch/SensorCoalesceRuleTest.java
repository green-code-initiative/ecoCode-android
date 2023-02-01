package io.ecocode.java.checks.energy.batch;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class SensorCoalesceRuleTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/batch/SensorCoalesceCheck.java")
                .withCheck(new SensorCoalesceRule())
                .verifyIssues();
    }
}
