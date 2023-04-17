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
package io.ecocode.ios.checks;

import io.ecocode.ios.antlr.AntlrContext;
import io.ecocode.ios.antlr.ParseTreeItemVisitor;
import io.ecocode.ios.rules.RepositoryRule;
import io.ecocode.ios.rules.RepositoryRuleParser;
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
    protected String rulesPath;
    protected String repositoryKey;

    private class Issue {
        protected Issue(String ruleId, int startIndex) {
            this.ruleId = ruleId;
            this.startIndex = startIndex;
        }
        protected String ruleId;
        protected int startIndex;
    }

    private List<Issue> issues = new ArrayList<>();

    private static List<RepositoryRule> rules = new ArrayList<>();
    public static RepositoryRule getRepositoryRule(String ruleId, String rulesPath) throws IOException {
        if (rules.isEmpty()) {
            RepositoryRuleParser repositoryRuleParser = new RepositoryRuleParser();
            rules = repositoryRuleParser.parse(rulesPath);
        }
        Optional<RepositoryRule> rule = rules.stream().filter(r -> r.getKey().equals(ruleId)).findFirst();
        if (rule.isPresent()) {
            return rule.get();
        } else {
            return null;
        }
    }

    protected RuleCheck(String ruleId, String rulesPath, String repositoryKey) {
        this.ruleId = ruleId;
        this.rulesPath = rulesPath;
        this.repositoryKey = repositoryKey;
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
                RepositoryRule rule = RuleCheck.getRepositoryRule(i.ruleId, rulesPath);
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
        recorder.recordIssues(reportIssues, repositoryKey);

        // Clear issues for next file
        issues.clear();
    }
}
