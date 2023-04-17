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
package io.ecocode.ios.swift;

import io.ecocode.ios.antlr.AntlrContext;
import io.ecocode.ios.antlr.ParseTreeItemVisitor;
import io.ecocode.ios.checks.RuleCheck;
import org.antlr.v4.runtime.tree.ParseTree;
import org.reflections.Reflections;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EcoCodeSwiftVisitor implements ParseTreeItemVisitor {
    private static final Logger LOGGER = Loggers.get(EcoCodeSwiftVisitor.class);

    private final List<RuleCheck> checks = new ArrayList<>();

    public EcoCodeSwiftVisitor() {
        // Load checks
        Reflections reflections = new Reflections("io.ecocode.ios.swift.checks");

        Set<Class<? extends RuleCheck>> allClasses = reflections.getSubTypesOf(RuleCheck.class);

        for (Class<? extends RuleCheck> clazz : allClasses) {
            Annotation[] annotations = clazz.getAnnotations();

            for (Annotation annotation : annotations) {
                if (annotation instanceof RegisterRule) {
                    try {
                        checks.add(clazz.getDeclaredConstructor().newInstance());
                    } catch (Exception e) {
                        LOGGER.warn("Unexpected error while instantiating rule " + clazz, e);
                    }
                }
            }
        }
    }

    @Override
    public void apply(ParseTree tree) {
        for (RuleCheck check : checks) {
            check.apply(tree);
        }
    }

    @Override
    public void fillContext(SensorContext context, AntlrContext antlrContext) {
        for (RuleCheck check : checks) {
            check.fillContext(context, antlrContext);
        }
    }

    public List<RuleCheck> getChecks() {
        return checks;
    }
}
