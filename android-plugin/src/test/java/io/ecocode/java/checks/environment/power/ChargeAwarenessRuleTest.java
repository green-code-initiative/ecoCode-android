package io.ecocode.java.checks.environment.power;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class ChargeAwarenessRuleTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/power/ChargeAwarenessCheck.java")
                .withCheck(new ChargeAwarenessRule())
                .verifyIssues();
    }
}
