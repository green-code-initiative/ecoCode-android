package io.ecocode.java.checks.environment.idleness;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class RigidAlarmRuleTest {

    @Test
    public void checkRigidAlarm() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/idleness/RigidAlarmCheck.java")
                .withCheck(new RigidAlarmRule())
                .verifyIssues();
    }
}
