package io.ecocode.checks.idleness;

import io.ecocode.antlr.generated.Swift5Parser;
import io.ecocode.checks.RuleCheck;
import org.antlr.v4.runtime.tree.ParseTree;

public class IdleTimerDisabledCheck extends RuleCheck {

    public IdleTimerDisabledCheck() {
        super("EIDL001");
    }

    @Override
    public void apply(ParseTree tree) {

        if (tree instanceof Swift5Parser.ExpressionContext) {
            Swift5Parser.ExpressionContext id = (Swift5Parser.ExpressionContext) tree;
            if (id.getText().equals("UIApplication.shared.isIdleTimerDisabled=true")) {
                this.recordIssue(ruleId, id.getStart().getStartIndex());
            }
        }
    }
}
