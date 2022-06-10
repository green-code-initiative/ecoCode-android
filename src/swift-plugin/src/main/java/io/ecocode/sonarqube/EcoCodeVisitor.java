package io.ecocode.sonarqube;

import io.ecocode.antlr.AntlrContext;
import io.ecocode.antlr.ParseTreeItemVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.sonar.api.batch.sensor.SensorContext;

public class EcoCodeVisitor implements ParseTreeItemVisitor {

    @Override
    public void apply(ParseTree tree) {

    }

    @Override
    public void fillContext(SensorContext context, AntlrContext antlrContext) {

    }
}
