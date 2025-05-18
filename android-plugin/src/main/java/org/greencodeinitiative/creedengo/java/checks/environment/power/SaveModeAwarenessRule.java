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
package org.greencodeinitiative.creedengo.java.checks.environment.power;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.MethodMatchers;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.NewClassTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.List;
import java.util.Optional;

/**
 * Checks the use of the BATTERY_CHANGED propriety in intentFilter or the use of the isPowerSaveMode() method
 */
@Rule(key = "GCI520")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-java", ruleKey = "EC520")
@DeprecatedRuleKey(repositoryKey = "ecoCode-java", ruleKey = "EPOW006")
public class SaveModeAwarenessRule extends IssuableSubscriptionVisitor {
    private static final Logger LOG = Loggers.get(SaveModeAwarenessRule.class);

    private static final String ACTION_BATTERY_CHANGED = "android.intent.action.BATTERY_CHANGED";
    private static final String INTENT_FILTER = "android.content.IntentFilter";
    private static final String ADVICE_MESSAGE = "Taking into account when the device is entering or exiting the power save mode is a good practice.";
    private final MethodMatchers addActionMatcher = MethodMatchers.create().ofTypes(INTENT_FILTER).names("addAction").withAnyParameters().build();
    private final MethodMatchers createIntentFilterMatcher = MethodMatchers.create().ofTypes(INTENT_FILTER).names("create").withAnyParameters().build();
    private final MethodMatchers isPowerSaveModeMatcher = MethodMatchers.create().ofTypes("android.os.PowerManager").names("isPowerSaveMode").withAnyParameters().build();

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return List.of(Tree.Kind.NEW_CLASS, Tree.Kind.METHOD_INVOCATION);
    }

    @Override
    public void visitNode(Tree tree) {
        super.visitNode(tree);
        try {
            if (tree.is(Tree.Kind.NEW_CLASS)) {
                NewClassTree nct = (NewClassTree) tree;
                if (nct.symbolType().fullyQualifiedName().equals(INTENT_FILTER)
                        && !nct.arguments().isEmpty()) {
                    Optional<Object> optionalNct = nct.arguments().get(0).asConstant();
                    if (optionalNct.isPresent()
                            && nct.arguments().get(0).symbolType().toString().equals("String")
                            && optionalNct.get().equals(ACTION_BATTERY_CHANGED)) {
                        reportIssue(nct.arguments().get(0), ADVICE_MESSAGE);
                    }
                }
            }
            if (tree.is(Tree.Kind.METHOD_INVOCATION)) {
                MethodInvocationTree mit = (MethodInvocationTree) tree;
                if ((addActionMatcher.matches(mit) || createIntentFilterMatcher.matches(mit))
                        && !mit.arguments().isEmpty()) {
                    mit.arguments().get(0).asConstant().ifPresentOrElse(o -> {
                        if (o instanceof String && o.equals(ACTION_BATTERY_CHANGED)) {
                            reportIssue(mit.arguments().get(0), ADVICE_MESSAGE);
                        }
                    }, () -> {
                        if (isPowerSaveModeMatcher.matches(mit)) {
                            reportIssue(mit, ADVICE_MESSAGE);
                        }
                    });
                } else {
                    if (isPowerSaveModeMatcher.matches(mit)) {
                        reportIssue(mit, ADVICE_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Error in visitNode : {}", e.getMessage(), e);
        }
    }
}
