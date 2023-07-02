/*
 * ecoCode SonarQube Plugin
 * Copyright (C) 2020-2021 Snapp' - Université de Pau et des Pays de l'Adour
 * mailto: contact@ecocode.io
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package io.ecocode.xml.checks.power;

import io.ecocode.xml.checks.helpers.CheckPermissionsRule;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * Checks manifest uses-permissions statement:
 * if the permission "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS" is found, report an issue.
 */
@Rule(key = "EC544")
@DeprecatedRuleKey(repositoryKey = "ecoCode-xml", ruleKey = "EPOW003")
public class IgnoreBatteryOptimizationsXmlRule extends CheckPermissionsRule {

    private static final String PERMISSION_NAME = "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS";
    private static final String ERROR_MESSAGE = "Battery optimization should not be ignored.";

    public IgnoreBatteryOptimizationsXmlRule() {
        super(PERMISSION_NAME, ERROR_MESSAGE);
    }
}
