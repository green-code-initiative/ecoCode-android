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
package io.ecocode.xml;

import io.ecocode.xml.checks.power.ExtraneousAnimationXmlRule;
import io.ecocode.xml.checks.batch.ServiceBootTimeXmlRule;
import io.ecocode.xml.checks.power.ChargeAwarenessXmlRule;
import io.ecocode.xml.checks.power.SaveModeAwarenessXmlRule;
import io.ecocode.xml.checks.sobriety.DarkUIBrightColorsXmlRule;
import io.ecocode.xml.checks.idleness.KeepCpuOnXmlRule;
import io.ecocode.xml.checks.idleness.KeepScreenOnXmlRule;
import io.ecocode.xml.checks.sobriety.DarkUIThemeXmlRule;
import io.ecocode.xml.checks.power.CompagnionInBackgroundXmlRule;
import io.ecocode.xml.checks.power.IgnoreBatteryOptimizationsXmlRule;
import io.ecocode.xml.checks.sobriety.HardwareAccelerationXmlRule;

import java.util.Arrays;
import java.util.List;

public class XmlCheckList {

    private XmlCheckList() {
    }

    public static List<Class<?>> getXmlChecks() {
        return Arrays.asList(
                DarkUIThemeXmlRule.class,
                KeepScreenOnXmlRule.class,
                DarkUIBrightColorsXmlRule.class,
                IgnoreBatteryOptimizationsXmlRule.class,
                KeepCpuOnXmlRule.class,
                CompagnionInBackgroundXmlRule.class,
                ChargeAwarenessXmlRule.class,
                ServiceBootTimeXmlRule.class,
                SaveModeAwarenessXmlRule.class,
                HardwareAccelerationXmlRule.class,
                ExtraneousAnimationXmlRule.class
        );
    }

}
