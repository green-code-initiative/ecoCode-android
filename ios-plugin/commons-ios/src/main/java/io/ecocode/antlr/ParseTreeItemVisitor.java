package io.ecocode.antlr;

import io.ecocode.antlr.AntlrContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.sonar.api.batch.sensor.SensorContext;

public interface ParseTreeItemVisitor {
    void apply(ParseTree tree);

    void fillContext(SensorContext context, AntlrContext antlrContext);
}