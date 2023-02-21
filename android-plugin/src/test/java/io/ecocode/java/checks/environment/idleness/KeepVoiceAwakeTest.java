package io.ecocode.java.checks.environment.idleness;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class KeepVoiceAwakeTest {

    @Test
    public void checkSetKeepAwakeToFalse() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/idleness/KeepVoiceAwakeToFalseCheck.java")
                .withCheck(new KeepVoiceAwakeRule())
                .verifyNoIssues();
    }

    @Test
    public void checkSetKeepAwakeToTrue() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/idleness/KeepVoiceAwakeCheck.java")
                .withCheck(new KeepVoiceAwakeRule())
                .verifyIssues();
    }

    @Test
    public void checkSetKeepAwakeNotPresent() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/idleness/KeepVoiceAwakeNotPresentCheck.java")
                .withCheck(new KeepVoiceAwakeRule())
                .verifyIssues();
    }
}
