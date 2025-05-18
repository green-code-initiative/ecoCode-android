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

import org.greencodeinitiative.creedengo.java.checks.helpers.TreeHelper;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.semantic.MethodMatchers;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Check if a method requestLocationUpdates is called from android.location.LocationManager package.
 * If minDistance argument value is 0, report an issue on the argument.
 */
@Rule(key = "GCI527")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-java", ruleKey = "EC527")
@DeprecatedRuleKey(repositoryKey = "ecoCode-java", ruleKey = "ESOB010")
public class ThriftyGeolocationMinDistanceRule extends IssuableSubscriptionVisitor {
    private static final String ERROR_MESSAGE = "Location updates should be done with a distance interval greater than 0.";
    private static final Logger LOG = Loggers.get(ThriftyGeolocationMinDistanceRule.class);

    private final MethodMatchers methodMatcher = MethodMatchers.create().ofTypes("android.location.LocationManager").names("requestLocationUpdates").withAnyParameters().addParametersMatcher().build();
    private final ArrayList<Tree> treesToReport = new ArrayList<>();
    private static final int ARGUMENT_VALUE_TO_CONTROL = 0;

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return List.of(Tree.Kind.METHOD_INVOCATION);
    }

    @Override
    public void visitNode(Tree tree) {
        if (tree.is(Tree.Kind.METHOD_INVOCATION)) {
            MethodInvocationTree mit = (MethodInvocationTree) tree;
            if (methodMatcher.matches(mit) && !mit.arguments().isEmpty()) {
                ExpressionTree firstArgument = mit.arguments().get(0);
                /*
                 * Here we want to know if the first parameter is a String,
                 * if it is, the minDistance Parameter will be at position 2
                 * else, the minDistance will be at position 1
                 */
                try {
                    if (firstArgument.symbolType().toString().equals("String") || firstArgument.is(Tree.Kind.NULL_LITERAL)) {
                        TreeHelper.literalValueControl(mit.arguments().get(2), treesToReport, ARGUMENT_VALUE_TO_CONTROL);
                    } else {
                        TreeHelper.literalValueControl(mit.arguments().get(1), treesToReport, ARGUMENT_VALUE_TO_CONTROL);
                    }
                } catch (Exception e) {
                    LOG.debug(String.format("%s Cannot evaluate requestLocationUpdates(...) argument value.", getClass().getName()));
                    LOG.debug("Exception: {}", e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void leaveFile(JavaFileScannerContext context) {
        if (!treesToReport.isEmpty()) {
            for (Tree tree : treesToReport) {
                reportIssue(tree, ERROR_MESSAGE);
            }
            treesToReport.clear();
        }
    }
}
