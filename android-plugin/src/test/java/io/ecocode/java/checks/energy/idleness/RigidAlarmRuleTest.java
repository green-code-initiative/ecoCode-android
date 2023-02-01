package io.ecocode.java.checks.energy.idleness;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class RigidAlarmRuleTest {

    @Test
    public void checkRigidAlarm() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/idleness/RigidAlarmCheck.java")
                .withCheck(new RigidAlarmRule())
                .verifyIssues();
    }
}
