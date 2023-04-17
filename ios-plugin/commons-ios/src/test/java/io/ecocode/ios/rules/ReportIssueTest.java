/*
 * ecoCode iOS plugin - Help the earth, adopt this green plugin for your applications
 * Copyright © 2022 green-code-initiative (https://www.ecocode.io/)
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
package io.ecocode.ios.rules;

import io.ecocode.ios.checks.ReportIssue;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportIssueTest {

    @Test
    public void equals() {
        ReportIssue issue1 = new ReportIssue("1", "msg", "/test/path", 20);
        ReportIssue issue2 = new ReportIssue("1", "msg", "/test/path", 20);

        assertThat(issue1).isEqualTo(issue2);
    }

    @Test
    public void notEqual() {
        ReportIssue issue1 = new ReportIssue("1", "msg", "/test/path", 20);
        ReportIssue issue2 = new ReportIssue("2", "msg", "/test/path", 20);

        assertThat(issue1).isNotEqualTo(issue2);
    }
}
