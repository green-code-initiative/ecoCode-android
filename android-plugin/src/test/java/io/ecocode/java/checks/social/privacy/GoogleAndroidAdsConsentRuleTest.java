package io.ecocode.java.checks.social.privacy;

import io.ecocode.java.checks.social.gdpr.GoogleAndroidAdsConsentRule;
import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class GoogleAndroidAdsConsentRuleTest {
    @Test
    public void verify() {

        CheckVerifier.newVerifier().onFile("src/test/files/social/privacy/GoogleTrackerGoogleCheck.java")
                .withCheck(new GoogleAndroidAdsConsentRule())
                .verifyNoIssues();
        CheckVerifier.newVerifier().onFile("src/test/files/social/privacy/GoogleAndroidAdsConsentCheck.java")
                .withCheck(new GoogleAndroidAdsConsentRule())
                .verifyIssueOnProject("Good Smell : User Consent");


    }
}
