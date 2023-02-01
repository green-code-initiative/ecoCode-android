package io.ecocode.java.checks.energy.idleness;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class KeepCpuOnRuleTest {

    @Test
    public void attributePresentTrue() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/idleness/KeepCpuOnCheck.java")
                .withCheck(new KeepCpuOnRule())
                .verifyIssues();
    }
}
