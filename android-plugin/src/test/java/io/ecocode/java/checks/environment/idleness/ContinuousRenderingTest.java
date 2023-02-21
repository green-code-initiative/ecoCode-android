package io.ecocode.java.checks.environment.idleness;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class ContinuousRenderingTest {

    @Test
    public void checkContinuousRendering() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/idleness/ContinuousRenderingCheck.java")
                .withCheck(new ContinuousRenderingRule())
                .verifyIssues();
    }
}
