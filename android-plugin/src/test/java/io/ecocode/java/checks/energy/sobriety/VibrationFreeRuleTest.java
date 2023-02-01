package io.ecocode.java.checks.energy.sobriety;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class VibrationFreeRuleTest {

    @Test
    public void verify() {

        CheckVerifier.newVerifier().onFiles("src/test/files/energy/sobriety/VibrationFreeCheckContext.java", "src/test/files/energy/sobriety/VibrationFreeCheckActivity.java")
                .withChecks(new VibrationFreeRule(), new VibrationFreeRule())
                .verifyIssues();
    }
}
