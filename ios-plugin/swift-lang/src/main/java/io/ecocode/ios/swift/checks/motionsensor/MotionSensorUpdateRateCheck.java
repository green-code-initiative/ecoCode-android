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
package io.ecocode.ios.swift.checks.motionsensor;

import io.ecocode.ios.checks.RuleCheck;
import io.ecocode.ios.swift.RegisterRule;
import io.ecocode.ios.swift.Swift;
import io.ecocode.ios.swift.antlr.generated.Swift5Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import static io.ecocode.ios.swift.checks.CheckHelper.isImportExisting;

@RegisterRule
public class MotionSensorUpdateRateCheck extends RuleCheck {
    Swift5Parser.Import_declarationContext importTree = null;
    private boolean sensorRateUpdated = false;
    private boolean importExist = false;

    public MotionSensorUpdateRateCheck() {
        super("ESOB003", Swift.RULES_PATH, Swift.REPOSITORY_KEY);
    }

    @Override
    public void apply(ParseTree tree) {
        if (isImportExisting(tree, "CoreMotion")) {
            importTree = (Swift5Parser.Import_declarationContext) tree;
            importExist = true;
        }

        if (tree instanceof Swift5Parser.ExpressionContext) {
            Swift5Parser.ExpressionContext id = (Swift5Parser.ExpressionContext) tree;
            if (id.getText().contains("desiredAccuracy")
                || id.getText().contains("activityType")
                || id.getText().contains("requestLocation")
                || id.getText().contains("magnetometerUpdateInterval")) {
                sensorRateUpdated = true;
            }
        }

        if (tree instanceof TerminalNodeImpl && tree.getText().equals("<EOF>")) {
            if (importExist && !sensorRateUpdated) {
                this.recordIssue(ruleId, importTree.getStart().getStartIndex());
            }
            importExist = false;
            sensorRateUpdated = false;
        }
    }
}
