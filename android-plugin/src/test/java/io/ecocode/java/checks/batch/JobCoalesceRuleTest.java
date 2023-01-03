package io.ecocode.java.checks.batch;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class JobCoalesceRuleTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/batch/JobCoalesceCheckAlarmManager.java")
                .withCheck(new JobCoalesceRule())
                .verifyIssues();

        CheckVerifier.newVerifier().onFile("src/test/files/batch/JobCoalesceCheckSyncAdapter.java")
                .withCheck(new JobCoalesceRule())
                .verifyIssues();
    }
}
