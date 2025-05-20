package io.ecocode.java.checks.social.privacy;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class GoogleAndroidAdsConsentRuleTest {
    @Test
    public void verify() {

        CheckVerifier.newVerifier().onFile("src/test/files/social/privacy/GoogleTrackerGoogleCheck.java")
                .withCheck(new GoogleAndroidAdsConsentRule())
                .verifyIssueOnProject("Check user consent by using com.google.android.ads.consent.* ");
        CheckVerifier.newVerifier().onFile("src/test/files/social/privacy/GoogleAndroidAdsConsentCheck.java")
                .withCheck(new GoogleAndroidAdsConsentRule())
                .verifyNoIssues();


    }
}
