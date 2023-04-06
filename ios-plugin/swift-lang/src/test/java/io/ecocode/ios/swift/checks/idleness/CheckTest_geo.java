package io.ecocode.ios.swift.checks.Geoloc;

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

import io.ecocode.ios.swift.checks.CheckTestHelper;
import org.junit.Test;
import org.sonar.api.batch.sensor.internal.SensorContextTester;
import org.sonar.api.batch.sensor.issue.Issue;
import org.sonar.api.batch.sensor.issue.IssueLocation;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckTest_geo {

    @Test
    public void Geo_trigger() {
        System.out.println("you should be triggeredd youuuuuuuu");
        SensorContextTester context = CheckTestHelper.analyzeTestFile("checks/Geo_trigger.swift");
        assertThat(context.allIssues()).hasSize(1);
        Optional<Issue> issue = context.allIssues().stream().findFirst();
        issue.ifPresent(i -> {
            assertThat(i.ruleKey().rule()).isEqualTo("ESOB003");
            assertThat(i.ruleKey().repository()).isEqualTo("ecoCode-swift");
            IssueLocation location = i.primaryLocation();
            assertThat(location.textRange().start().line()).isEqualTo(11);
        });
    }
/* 
    @Test
    public void Geo_no_trigger() {
        SensorContextTester context = CheckTestHelper.analyzeTestFile("checks/Geo_no_trigger.swift");
        assertThat(context.allIssues()).isEmpty();
    }
*/
}
