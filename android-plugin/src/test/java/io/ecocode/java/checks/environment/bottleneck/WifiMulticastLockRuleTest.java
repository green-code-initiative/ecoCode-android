package io.ecocode.java.checks.environment.bottleneck;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class WifiMulticastLockRuleTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/bottleneck/WifiMulticastLockCheck.java")
                .withCheck(new WifiMulticastLockRule())
                .verifyIssues();
    }

    @Test
    public void verifyNoIssue() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/bottleneck/WifiMulticastLockNoIssueCheck.java")
                .withCheck(new WifiMulticastLockRule())
                .verifyNoIssues();
    }
}
