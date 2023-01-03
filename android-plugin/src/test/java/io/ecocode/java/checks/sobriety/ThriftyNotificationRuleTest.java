package io.ecocode.java.checks.sobriety;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class ThriftyNotificationRuleTest {

    @Test
    public void verify() {

        CheckVerifier.newVerifier().onFile("src/test/files/sobriety/ThriftyNotificationCheckBuilder.java")
                .withCheck(new ThriftyNotificationRule())
                .verifyIssues();

        CheckVerifier.newVerifier().onFile("src/test/files/sobriety/ThriftyNotificationCheckChannel.java")
                .withCheck(new ThriftyNotificationRule())
                .verifyIssues();
    }
}
