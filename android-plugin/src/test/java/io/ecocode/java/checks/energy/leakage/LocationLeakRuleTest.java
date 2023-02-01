package io.ecocode.java.checks.energy.leakage;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class LocationLeakRuleTest {

    @Test
    public void LocationOnlyRegister() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/leakage/LocationLeakCheckIssue.java")
                .withCheck(new LocationLeakRule())
                .verifyIssues();
    }

    @Test
    public void LocationRegisterAndUnregister() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/leakage/LocationLealCheckNoIssue.java")
                .withCheck(new LocationLeakRule())
                .verifyNoIssues();
    }
}
