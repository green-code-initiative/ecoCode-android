package io.ecocode.checks;

import io.ecocode.Swift;
import io.ecocode.antlr.AntlrContext;
import io.ecocode.antlr.ParseTreeItemVisitor;
import io.ecocode.rules.RepositoryRule;
import io.ecocode.rules.RepositoryRuleParser;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public abstract class RuleCheck implements ParseTreeItemVisitor {

    private static final Logger LOGGER = Loggers.get(RuleCheck.class);

    protected String ruleId;

    private class Issue {
        protected Issue(String ruleId, int startIndex) {
            this.ruleId = ruleId;
            this.startIndex = startIndex;
        }
        protected String ruleId;
        protected int startIndex;
    };
    private List<Issue> issues = new ArrayList<>();

    private static List<RepositoryRule> rules = new ArrayList<>();
    public static synchronized RepositoryRule getRepositoryRule(String ruleId) throws IOException {
        if (rules.isEmpty()) {
            RepositoryRuleParser repositoryRuleParser = new RepositoryRuleParser();
            rules = repositoryRuleParser.parse(Swift.RULES_PATH);
        }
        return rules.stream().filter(r -> r.getKey().equals(ruleId)).findFirst().get();
    }

    public RuleCheck(String ruleId) {
        this.ruleId = ruleId;
    }

    protected void recordIssue(String ruleId, int startIndex) {
        issues.add(new Issue(ruleId, startIndex));
    }

    @Override
    public void fillContext(SensorContext context, AntlrContext antlrContext) {

        final InputFile file = antlrContext.getFile();
        if (file == null) {
            return;
        }

        // Build ReportIssue list
        List<ReportIssue> reportIssues = new ArrayList<>();
        for (Issue i : issues) {
            // Compute location
            int[] loc = antlrContext.getLineAndColumn(i.startIndex);
            // Retrieve rule data
            try {
                RepositoryRule rule = RuleCheck.getRepositoryRule(i.ruleId);
                if (rule != null) {
                    reportIssues.add(new ReportIssue(i.ruleId, rule.getDescription(), file.toString(), loc[0]));
                } else {
                    LOGGER.warn(format("Failed to identify rule %s", i.ruleId));
                }

            } catch (IOException e) {
                LOGGER.warn(format("Failed to identify rule %s", i.ruleId),e);
            }
        }

        // Record
        ReportIssueRecorder recorder = new ReportIssueRecorder(context);
        recorder.recordIssues(reportIssues, Swift.REPOSITORY_KEY);

        // Clear issues for next file
        issues.clear();
    }
}
