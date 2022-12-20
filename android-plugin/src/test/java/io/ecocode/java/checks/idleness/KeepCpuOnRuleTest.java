package io.ecocode.java.checks.idleness;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class KeepCpuOnRuleTest {

    @Test
    public void attributePresentTrue() {
        CheckVerifier.newVerifier().onFile("src/test/files/idleness/KeepCpuOnCheck.java")
                .withCheck(new KeepCpuOnRule())
                .verifyIssues();
    }
}
