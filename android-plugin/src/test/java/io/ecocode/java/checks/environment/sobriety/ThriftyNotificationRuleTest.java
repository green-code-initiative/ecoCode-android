package io.ecocode.java.checks.environment.sobriety;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class ThriftyNotificationRuleTest {

    @Test
    public void verify() {

        CheckVerifier.newVerifier().onFile("src/test/files/environment/sobriety/ThriftyNotificationCheckBuilder.java")
                .withCheck(new ThriftyNotificationRule())
                .verifyIssues();

        CheckVerifier.newVerifier().onFile("src/test/files/environment/sobriety/ThriftyNotificationCheckChannel.java")
                .withCheck(new ThriftyNotificationRule())
                .verifyIssues();
    }
}
