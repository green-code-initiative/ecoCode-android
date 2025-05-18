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
package io.ecocode.java.checks.environment.sobriety;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.MethodMatchers;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.List;

/**
 * Check the call of "setSound", "setVibrate" & "setVibrationPattern" of "android.app.NotificationChannel" & "android.app.Notification$Builder".
 */
@Rule(key = "GCI529")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-java", ruleKey = "EC529")
@DeprecatedRuleKey(repositoryKey = "ecoCode-java", ruleKey = "ESOB012")
public class ThriftyNotificationRule extends IssuableSubscriptionVisitor {
    private static final String ERROR_MESSAGE = "Avoid using vibration or sound when notifying the users to use less energy.";
    private final MethodMatchers notificationHardwareCallMethodMatchers = MethodMatchers.or(
            MethodMatchers.create().ofTypes("android.app.NotificationChannel").names("setVibrationPattern").withAnyParameters().build(),
            MethodMatchers.create().ofTypes("android.app.NotificationChannel").names("setSound").withAnyParameters().build(),
            MethodMatchers.create().ofTypes("android.app.Notification$Builder").names("setVibrate").withAnyParameters().build(),
            MethodMatchers.create().ofTypes("android.app.Notification$Builder").names("setSound").withAnyParameters().build()
    );

    public ThriftyNotificationRule() {
        super();
    }

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return List.of(Tree.Kind.METHOD_INVOCATION);
    }

    @Override
    public void visitNode(Tree tree) {
        if (tree.is(Tree.Kind.METHOD_INVOCATION)) {
            MethodInvocationTree mit = (MethodInvocationTree) tree;
            if (notificationHardwareCallMethodMatchers.matches(mit)
                    && isOneArgumentNull(mit.arguments().toArray())) {
                reportIssue(mit, ERROR_MESSAGE);
            }
        }
    }

    /**
     * Method that checks if one of the arguments of the function currently matching is null
     *
     * @param arguments arguments of the function currently matching
     * @return true if no arguments are null
     */
    private boolean isOneArgumentNull(Object[] arguments) {
        for (Object argument : arguments) {
            if (((ExpressionTree) argument).kind() != Tree.Kind.NULL_LITERAL) {
                return true;
            }
        }
        return false;
    }
}
