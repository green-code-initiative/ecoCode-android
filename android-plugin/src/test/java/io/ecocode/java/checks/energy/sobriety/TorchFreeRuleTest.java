package io.ecocode.java.checks.energy.sobriety;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class TorchFreeRuleTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/sobriety/TorchFreeCheck.java")
                .withCheck(new TorchFreeRule())
                .verifyIssues();
    }
}
