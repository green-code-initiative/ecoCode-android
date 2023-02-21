package io.ecocode.java.checks.social.privacy;

import io.ecocode.java.checks.social.privacy.GoogleTrackerRule;
import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class GoogleTrackerRuleTest {

    @Test
    public void verify() {

        CheckVerifier.newVerifier().onFile("src/test/files/social/privacy/GoogleTrackerGoogleCheck.java")
                .withCheck(new GoogleTrackerRule())
                .verifyIssues();
        CheckVerifier.newVerifier().onFile("src/test/files/social/privacy/GoogleTrackerFirebaseCheck.java")
                .withCheck(new GoogleTrackerRule())
                .verifyIssues();
        CheckVerifier.newVerifier().onFile("src/test/files/social/privacy/GoogleTrackerGoogleAndFirebaseCheck.java")
                .withCheck(new GoogleTrackerRule())
                .verifyIssues();

    }
}
