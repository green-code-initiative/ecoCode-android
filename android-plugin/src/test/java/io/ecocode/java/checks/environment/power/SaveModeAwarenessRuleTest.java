package io.ecocode.java.checks.environment.power;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class SaveModeAwarenessRuleTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/power/SaveModeAwarenessCheckIntentFilter.java")
                .withCheck(new SaveModeAwarenessRule())
                .verifyIssues();

        CheckVerifier.newVerifier().onFile("src/test/files/environment/power/SaveModeAwarenessCheckPowerManager.java")
                .withCheck(new SaveModeAwarenessRule())
                .verifyIssues();
    }
}