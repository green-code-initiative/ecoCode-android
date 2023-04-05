package io.ecocode.ios.swift;

import io.ecocode.ios.checks.RuleCheck;
import org.junit.Test;
import org.reflections.Reflections;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class EcoCodeSwiftVisitorTest {
    @Test
    public void analyze() throws Throwable {
        Reflections reflections = new Reflections("io.ecocode.ios.swift.checks");
        Set<Class<? extends RuleCheck>> allClasses = reflections.getSubTypesOf(RuleCheck.class);

        EcoCodeSwiftVisitor ecoCodeSwiftVisitor = new EcoCodeSwiftVisitor();
        assertThat(ecoCodeSwiftVisitor.getChecks()).hasSize(allClasses.size());
    }
}
