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
package io.ecocode.xml.checks.batch;

import io.ecocode.xml.checks.XPathSimpleCheck;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * Checks manifest intent-filter statement: if the action is BOOT_COMPLETED, report a bad practice
 */
@Rule(key = "GCI540")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-xml", ruleKey = "EC540")
@DeprecatedRuleKey(repositoryKey = "ecoCode-xml", ruleKey = "EBAT001")
public class ServiceBootTimeXmlRule extends XPathSimpleCheck {

    private static final String SERVICE_BOOT_TIME_ATTRIBUTE = "//manifest/application/receiver/intent-filter/action/@name[. = \"android.intent.action.BOOT_COMPLETED\"]";
    private static final String ERROR_MESSAGE = "Avoid using a receiver to launch a service with BOOT_COMPLETED to drain less battery";

    @Override
    protected String getMessage() {
        return ERROR_MESSAGE;
    }

    @Override
    protected String getXPathExpressionString() {
        return SERVICE_BOOT_TIME_ATTRIBUTE;
    }
}
