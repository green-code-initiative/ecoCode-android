package io.ecocode.java.checks.energy.optimized_api;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class BluetoothLowEnergyRuleTest {

    @Test
    public void verify() {
        CheckVerifier.newVerifier().onFile("src/test/files/energy/optimized_api/BluetoothLowEnergyCheckBothBleBc.java")
                .withCheck(new BluetoothLowEnergyRule())
                .verifyIssues();

        CheckVerifier.newVerifier().onFile("src/test/files/energy/optimized_api/BluetoothLowEnergyCheckOnlyBc.java")
                .withCheck(new BluetoothLowEnergyRule())
                .verifyIssues();

        CheckVerifier.newVerifier().onFile("src/test/files/energy/optimized_api/BluetoothLowEnergyCheckOnlyBle.java")
                .withCheck(new BluetoothLowEnergyRule())
                .verifyIssues();

        CheckVerifier.newVerifier().onFile("src/test/files/energy/optimized_api/BluetoothLowEnergyCheckWildcard.java")
                .withCheck(new BluetoothLowEnergyRule())
                .verifyIssues();
    }
}
