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
package io.ecocode.ios.rules;

import io.ecocode.ios.rules.JSONRulesDefinition;
import org.junit.Test;
import org.sonar.api.server.rule.RulesDefinition;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONRulesDefinitionTest {

    @Test
    public void define() {

        JSONRulesDefinition rulesDefinition = new JSONRulesDefinition("rep_key", "rep_name", "lang", "/rules/rules.json") {};


        RulesDefinition.Context context = new RulesDefinition.Context();
        rulesDefinition.define(context);

        RulesDefinition.Repository repository = context.repository("rep_key");
        assertThat(repository).isNotNull();
        assertThat(repository.name()).isEqualTo("rep_name");
        assertThat(repository.language()).isEqualTo("lang");
        assertThat(repository.rules()).isNotEmpty();
        assertThat(repository.rules().get(0).tags()).isNotEmpty();


    }
}
