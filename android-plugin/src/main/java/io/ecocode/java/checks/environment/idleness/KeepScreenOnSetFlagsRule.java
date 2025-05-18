/*
 * ecoCode Android plugin - Provides rules to reduce the environmental footprint of your Android applications
 * Copyright © 2020 Green Code Initiative (contact@ecocode.io)
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
package io.ecocode.java.checks.environment.idleness;

import io.ecocode.java.checks.helpers.constant.FlagOnMethodCheck;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * Check the call of "FLAG_KEEP_SCREEN_ON" on the method "setFlags" (both parameters) of "android.view.Window".
 */
@Rule(key = "GCI506")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-java", ruleKey = "EC506")
@DeprecatedRuleKey(repositoryKey = "ecoCode-java", ruleKey = "EIDL002")
public class KeepScreenOnSetFlagsRule extends FlagOnMethodCheck {

    public KeepScreenOnSetFlagsRule() {
        super("setFlags", "android.view.Window", 0x00000080, 0, 1);
    }

    @Override
    public String getMessage() {
        return "Keeping the screen on should be avoided to avoid draining the battery.";
    }
}
