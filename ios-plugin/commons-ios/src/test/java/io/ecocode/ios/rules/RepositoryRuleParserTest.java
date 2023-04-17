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

import io.ecocode.ios.rules.RepositoryRule;
import io.ecocode.ios.rules.RepositoryRuleParser;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositoryRuleParserTest {

    @Test
    public void parse() throws Throwable {

        RepositoryRuleParser parser = new RepositoryRuleParser();
        List<RepositoryRule> repositoryRules = parser.parse("/rules/rules.json");
        assertThat(repositoryRules).hasSize(2);

        RepositoryRule repositoryRule1 = repositoryRules.get(0);
        assertThat(repositoryRule1.getKey()).isEqualTo("rule1");
        assertThat(repositoryRule1.getName()).isEqualTo("Rule 1");
        assertThat(repositoryRule1.getDescription()).isEqualTo("This is rule 1.");
        assertThat(repositoryRule1.getSeverity()).isEqualTo("MINOR");
        assertThat(repositoryRule1.getDebt()).isNotNull();
        assertThat(repositoryRule1.getDebt().getFunction()).isEqualTo("CONSTANT_ISSUE");
        assertThat(repositoryRule1.getDebt().getOffset()).isEqualTo("5min");
        assertThat(repositoryRule1.getTags()).hasSize(2);
        assertThat(repositoryRule1.getTags().get(0)).isEqualTo("tag1");
        assertThat(repositoryRule1.getTags().get(1)).isEqualTo("tag2");

        RepositoryRule repositoryRule2 = repositoryRules.get(1);
        assertThat(repositoryRule2.getKey()).isEqualTo("rule2");
        assertThat(repositoryRule2.getName()).isEqualTo("Rule 2");
        assertThat(repositoryRule2.getDescription()).isEqualTo("This is rule 2.");
        assertThat(repositoryRule2.getSeverity()).isEqualTo("MAJOR");
        assertThat(repositoryRule2.getDebt()).isNotNull();
        assertThat(repositoryRule2.getDebt().getFunction()).isEqualTo("CONSTANT_ISSUE");
        assertThat(repositoryRule2.getDebt().getOffset()).isEqualTo("15min");
        assertThat(repositoryRule2.getTags()).hasSize(1);
        assertThat(repositoryRule2.getTags().get(0)).isEqualTo("tag1");
    }
}
