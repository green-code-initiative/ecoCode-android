package io.ecocode.java.checks.environment.leakage;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class CameraLeakRuleTest {

    @Test
    public void CameraOnlyRegister() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/leakage/CameraLeakCheckIssue.java")
                .withCheck(new CameraLeakRule())
                .verifyIssues();
    }

    @Test
    public void CameraRegisterAndUnregister() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/leakage/CameraLeakCheckNoIssue.java")
                .withCheck(new CameraLeakRule())
                .verifyNoIssues();
    }
}
