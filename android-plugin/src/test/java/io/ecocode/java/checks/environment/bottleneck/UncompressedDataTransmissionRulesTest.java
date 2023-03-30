package io.ecocode.java.checks.environment.bottleneck;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class UncompressedDataTransmissionRulesTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/bottleneck/UncompressedDataTransmissionCheck.java")
                .withCheck(new UncompressedDataTransmissionRule())
                .verifyIssues();
    }

}
