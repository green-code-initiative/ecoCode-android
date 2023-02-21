package io.ecocode.java.checks.environment.leakage;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class MediaLeakMediaRecorderRuleTest {

    @Test
    public void onlyConstructor() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/leakage/MediaLeakMediaRecorderCheckIssue.java")
                .withCheck(new MediaLeakMediaRecorderRule())
                .verifyIssues();
    }

    @Test
    public void constructorAndRelease() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/leakage/MediaLeakMediaRecorderCheckNoIssue.java")
                .withCheck(new MediaLeakMediaRecorderRule())
                .verifyNoIssues();
    }
}
