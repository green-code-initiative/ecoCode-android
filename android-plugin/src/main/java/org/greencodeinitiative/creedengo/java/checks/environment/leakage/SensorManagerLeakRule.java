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
package org.greencodeinitiative.creedengo.java.checks.environment.leakage;

import org.greencodeinitiative.creedengo.java.checks.helpers.OpeningClosingMethodCheck;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * Check that SensorManagerLeakRule#unregisterListener() method is closed after SensorManagerLeakRule#registerListener().
 *
 * @see OpeningClosingMethodCheck
 */
@Rule(key = "GCI514")
@DeprecatedRuleKey(repositoryKey = "ecoCode-android-java", ruleKey = "EC514")
@DeprecatedRuleKey(repositoryKey = "ecoCode-java", ruleKey = "ELEA004")
public class SensorManagerLeakRule extends OpeningClosingMethodCheck {

    public SensorManagerLeakRule() {
        super("registerListener", "unregisterListener", "android.hardware.SensorManager");
    }

    @Override
    public String getMessage() {
        return "Failing to call android.hardware.SensorManager#unregisterListener() can drain the battery in just a few hours.";
    }
}
