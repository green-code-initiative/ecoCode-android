package io.ecocode.java.checks.environment.sobriety;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class VibrationFreeRuleTest {

    @Test
    public void verify() {

        CheckVerifier.newVerifier().onFiles("src/test/files/environment/sobriety/VibrationFreeCheckContext.java", "src/test/files/environment/sobriety/VibrationFreeCheckActivity.java")
                .withChecks(new VibrationFreeRule(), new VibrationFreeRule())
                .verifyIssues();
    }
}
