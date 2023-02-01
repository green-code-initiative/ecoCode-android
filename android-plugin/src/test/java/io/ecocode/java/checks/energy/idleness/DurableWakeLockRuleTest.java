package io.ecocode.java.checks.energy.idleness;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class DurableWakeLockRuleTest {

    @Test
    public void attributePresentTrue() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/idleness/DurableWakeLockCheck.java")
                .withCheck(new DurableWakeLockRule())
                .verifyIssues();
    }
}
