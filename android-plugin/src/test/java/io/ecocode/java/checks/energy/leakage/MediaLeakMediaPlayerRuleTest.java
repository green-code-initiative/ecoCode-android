package io.ecocode.java.checks.energy.leakage;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class MediaLeakMediaPlayerRuleTest {

    @Test
    public void onlyConstructor() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/leakage/MediaLeakMediaPlayerCheckIssue.java")
                .withCheck(new MediaLeakMediaPlayerRule())
                .verifyIssues();
    }

    @Test
    public void constructorAndRelease() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/leakage/MediaLeakMediaPlayerCheckNoIssue.java")
                .withCheck(new MediaLeakMediaPlayerRule())
                .verifyNoIssues();
    }
}
