package io.ecocode.java.checks.environment.idleness;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class KeepScreenOnAddFlagsRuleTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/idleness/KeepScreenOnAddFlagsCheck.java")
                .withCheck(new KeepScreenOnAddFlagsRule())
                .verifyIssues();
    }
}
