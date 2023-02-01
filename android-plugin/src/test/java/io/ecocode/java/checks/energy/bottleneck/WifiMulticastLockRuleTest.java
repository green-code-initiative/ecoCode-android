package io.ecocode.java.checks.energy.bottleneck;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class WifiMulticastLockRuleTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/bottleneck/WifiMulticastLockCheck.java")
                .withCheck(new WifiMulticastLockRule())
                .verifyIssues();
    }

    @Test
    public void verifyNoIssue() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/bottleneck/WifiMulticastLockNoIssueCheck.java")
                .withCheck(new WifiMulticastLockRule())
                .verifyNoIssues();
    }
}
