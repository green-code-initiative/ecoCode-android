package io.ecocode.java.checks.sobriety;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class ThriftyGeolocationCheckTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/sobriety/ThriftyGeolocationCheck.java")
                .withChecks(new ThriftyGeolocationMinTimeRule(), new ThriftyGeolocationMinDistanceRule())
                .verifyIssues();
    }
}
