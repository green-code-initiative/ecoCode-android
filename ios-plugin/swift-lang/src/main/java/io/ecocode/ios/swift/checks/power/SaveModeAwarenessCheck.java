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
package io.ecocode.ios.swift.checks.power;

import io.ecocode.ios.checks.RuleCheck;
import io.ecocode.ios.swift.RegisterRule;
import io.ecocode.ios.swift.Swift;
import io.ecocode.ios.swift.antlr.generated.Swift5Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

@RegisterRule
public class SaveModeAwarenessCheck extends RuleCheck {
    public SaveModeAwarenessCheck() {
        super("EPOW002", Swift.RULES_PATH, Swift.REPOSITORY_KEY);
    }

    private static final String PROCESS_INFO = "ProcessInfo.processInfo.isLowPowerModeEnabled";
    private static final String POWER_STATE_NOTIFICATION_FULL = "Notification.Name.NSProcessInfoPowerStateDidChange";
    private static final String POWER_STATE_NOTIFICATION_SHORT = ".NSProcessInfoPowerStateDidChange";

    private static final List<String> EXPRESSIONS_TO_CHECK = List.of(
        PROCESS_INFO,
        POWER_STATE_NOTIFICATION_FULL,
        POWER_STATE_NOTIFICATION_SHORT
    );

    @Override
    public void apply(ParseTree tree) {
        if (tree instanceof Swift5Parser.Postfix_expressionContext) {
            Swift5Parser.Postfix_expressionContext id = (Swift5Parser.Postfix_expressionContext) tree;
            if (EXPRESSIONS_TO_CHECK.contains(id.getText())) {
                this.recordIssue(ruleId, id.getStart().getStartIndex());
            }
        }
    }
}
