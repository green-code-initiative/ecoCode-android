/*
 * creedengo Android plugin - Provides rules to reduce the environmental footprint of your Android applications
 * Copyright © 2020 Green Code Initiative (contact@green-code-initiative.org)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.greencodeinitiative.creedengo.java.checks.environment.idleness;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class KeepVoiceAwakeTest {

    @Test
    public void checkSetKeepAwakeToFalse() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/idleness/KeepVoiceAwakeToFalseCheck.java")
                .withCheck(new KeepVoiceAwakeRule())
                .verifyNoIssues();
    }

    @Test
    public void checkSetKeepAwakeToTrue() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/idleness/KeepVoiceAwakeCheck.java")
                .withCheck(new KeepVoiceAwakeRule())
                .verifyIssues();
    }

    @Test
    public void checkSetKeepAwakeNotPresent() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/idleness/KeepVoiceAwakeNotPresentCheck.java")
                .withCheck(new KeepVoiceAwakeRule())
                .verifyIssues();
    }
}
