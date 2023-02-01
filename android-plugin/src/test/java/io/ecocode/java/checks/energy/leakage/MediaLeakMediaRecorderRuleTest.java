package io.ecocode.java.checks.energy.leakage;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class MediaLeakMediaRecorderRuleTest {

    @Test
    public void onlyConstructor() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/leakage/MediaLeakMediaRecorderCheckIssue.java")
                .withCheck(new MediaLeakMediaRecorderRule())
                .verifyIssues();
    }

    @Test
    public void constructorAndRelease() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/leakage/MediaLeakMediaRecorderCheckNoIssue.java")
                .withCheck(new MediaLeakMediaRecorderRule())
                .verifyNoIssues();
    }
}
