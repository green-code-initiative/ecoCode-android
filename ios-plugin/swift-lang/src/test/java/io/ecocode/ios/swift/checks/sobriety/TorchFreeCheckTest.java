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
package io.ecocode.ios.swift.checks.environment.sobriety;

import io.ecocode.ios.swift.checks.CheckTestHelper;
import org.junit.Test;
import org.sonar.api.batch.sensor.internal.SensorContextTester;
import org.sonar.api.batch.sensor.issue.Issue;
import org.sonar.api.batch.sensor.issue.IssueLocation;

import static org.assertj.core.api.Assertions.assertThat;

public class TorchFreeCheckTest {

    @Test
    public void should_detect_torch_on_usage() {
        assertTorchIssue("checks/sobriety/torchUsage_on_trigger.swift", 12);
    }

    @Test
    public void should_detect_torch_typeInference_on_usage() {
        assertTorchIssue("checks/sobriety/torchUsage_typeInference_on_trigger.swift", 12);
    }

    @Test
    public void should_detect_level_torch_usage() {
        assertTorchIssue("checks/sobriety/torchUsage_level_trigger.swift", 12);
    }

    @Test
    public void should_not_detect_torch_usage() {
        SensorContextTester context = CheckTestHelper.analyzeTestFile("checks/sobriety/no_torchUsage_trigger.swift");
        assertThat(context.allIssues()).isEmpty();
    }
    
    private void assertTorchIssue(String file, int line) {
        SensorContextTester context = CheckTestHelper.analyzeTestFile(file);
        assertThat(context.allIssues()).hasSize(1)
                .allSatisfy(issue -> {
                    assertIssue(issue, line);
                });
    }
    
    private void assertIssue(Issue issue, int line) {
        assertThat(issue.ruleKey().rule()).isEqualTo("ESOB006");
        assertThat(issue.ruleKey().repository()).isEqualTo("ecoCode-swift");
        IssueLocation location = issue.primaryLocation();
        assertThat(location.textRange().start().line()).isEqualTo(line);
    }
}
