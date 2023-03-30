package io.ecocode.java.checks.environment.optimized_api;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class FusedLocationCheckTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/optimized_api/FusedLocationCheck.java")
                .withCheck(new FusedLocationRule())
                .verifyIssues();
    }

    @Test
    public void verifyNoIssue() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/optimized_api/FusedLocationCheckNoIssue.java")
                .withCheck(new FusedLocationRule())
                .verifyNoIssues();
    }
}
