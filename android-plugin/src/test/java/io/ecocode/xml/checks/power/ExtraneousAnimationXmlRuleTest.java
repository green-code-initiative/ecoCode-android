package io.ecocode.xml.checks.power;

import org.junit.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

public class ExtraneousAnimationXmlRuleTest {
    @Test
    public void shouldTriggerRule() {
        SonarXmlCheckVerifier.verifyIssues("res/animator/fade_in.xml", new ExtraneousAnimationXmlRule());
    }

    @Test
    public void shouldNotTriggerRule() {
        SonarXmlCheckVerifier.verifyNoIssue("res/fade_out.xml", new ExtraneousAnimationXmlRule());
    }
}