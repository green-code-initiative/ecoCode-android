package io.ecocode.java.checks.environment.idleness;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class KeepCpuOnRuleTest {

    @Test
    public void attributePresentTrue() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/idleness/KeepCpuOnCheck.java")
                .withCheck(new KeepCpuOnRule())
                .verifyIssues();
    }
}
