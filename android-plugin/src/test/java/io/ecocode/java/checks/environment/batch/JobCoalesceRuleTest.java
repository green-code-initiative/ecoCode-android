package io.ecocode.java.checks.environment.batch;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class JobCoalesceRuleTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/batch/JobCoalesceCheckAlarmManager.java")
                .withCheck(new JobCoalesceRule())
                .verifyIssues();

        CheckVerifier.newVerifier().onFile("src/test/files/environment/batch/JobCoalesceCheckSyncAdapter.java")
                .withCheck(new JobCoalesceRule())
                .verifyIssues();
    }
}
