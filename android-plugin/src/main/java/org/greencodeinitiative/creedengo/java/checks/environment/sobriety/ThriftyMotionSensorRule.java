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
package org.greencodeinitiative.creedengo.java.checks.environment.sobriety;

import org.greencodeinitiative.creedengo.java.checks.helpers.constant.FlagOnMethodCheck;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * Check the call of "TYPE_ROTATION_VECTOR" on the method "getDefaultSensor" of "android.hardware.SensorManager".
 */
@Rule(key = "GCI521")
@DeprecatedRuleKey(repositoryKey = "ecoCode-android-java", ruleKey = "EC521")
@DeprecatedRuleKey(repositoryKey = "ecoCode-java", ruleKey = "ESOB001")
public class ThriftyMotionSensorRule extends FlagOnMethodCheck {

    public ThriftyMotionSensorRule() {
        super("getDefaultSensor", "android.hardware.SensorManager", 11, 0);
    }

    @Override
    public String getMessage() {
        return "Prefer using TYPE_GEOMAGNETIC_ROTATION_VECTOR instead of TYPE_ROTATION_VECTOR to use less energy.";
    }
}
