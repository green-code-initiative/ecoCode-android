/*
 * ecoCode iOS plugin - Help the earth, adopt this green plugin for your applications
 * Copyright © 2022 Green code Initiative (https://www.ecocode.io/)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.ecocode.ios.swift.checks.environment.idleness;

import io.ecocode.ios.swift.checks.CheckTestHelper;
import org.junit.Test;
import org.sonar.api.batch.sensor.internal.SensorContextTester;
import org.sonar.api.batch.sensor.issue.Issue;
import org.sonar.api.batch.sensor.issue.IssueLocation;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

<<<<<<< HEAD:ios-plugin/swift-lang/src/test/java/io/ecocode/ios/swift/checks/idleness/IdleTimerDisabledCheckTest.java
public class IdleTimerDisabledCheckTest {
=======
public class IdleTimerDisabledTests {
>>>>>>> 29e9b27... [ESOBOO6 - iOS] Add TUs:ios-plugin/swift-lang/src/test/java/io/ecocode/ios/swift/checks/environment/idleness/IdleTimerDisabledTests.java

    @Test
    public void idleTimerDisabled_trigger() {
        SensorContextTester context = CheckTestHelper.analyzeTestFile("checks/IdleTimerDisabled_trigger.swift");
        assertThat(context.allIssues()).hasSize(1);
        Optional<Issue> issue = context.allIssues().stream().findFirst();
        issue.ifPresent(i -> {
            assertThat(i.ruleKey().rule()).isEqualTo("EIDL001");
            assertThat(i.ruleKey().repository()).isEqualTo("ecoCode-swift");
            IssueLocation location = i.primaryLocation();
            assertThat(location.textRange().start().line()).isEqualTo(11);
        });
    }

    @Test
    public void idleTimerDisabled_no_trigger() {
        SensorContextTester context = CheckTestHelper.analyzeTestFile("checks/IdleTimerDisabled_no_trigger.swift");
        assertThat(context.allIssues()).isEmpty();
    }
}
