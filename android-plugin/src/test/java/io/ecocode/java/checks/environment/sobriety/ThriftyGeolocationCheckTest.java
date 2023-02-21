package io.ecocode.java.checks.environment.sobriety;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class ThriftyGeolocationCheckTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/sobriety/ThriftyGeolocationCheck.java")
                .withChecks(new ThriftyGeolocationMinTimeRule(), new ThriftyGeolocationMinDistanceRule())
                .verifyIssues();
    }
}
