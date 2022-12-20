package io.ecocode.java.checks.idleness;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class KeepScreenOnSetFlagsRuleTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/idleness/KeepScreenOnSetFlagsCheck.java")
                .withCheck(new KeepScreenOnSetFlagsRule())
                .verifyIssues();
    }
}
