package io.ecocode.java.checks.environment.sobriety;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class HighFrameRateCheckTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/sobriety/HighFrameRateCheck.java")
                .withChecks(new HighFrameRateRule())
                .verifyIssues();
    }

}