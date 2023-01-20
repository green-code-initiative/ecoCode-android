package io.ecocode.ios.swift.antlr;

import org.antlr.v4.runtime.Token;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;

public class SwiftAntlrContextTest {
    @Test
    public void linesDetection() throws Throwable {
        String s = "let test = \"test\"";

        SwiftAntlrContext result = new SwiftAntlrContext();
        result.loadFromStreams(null, IOUtils.toInputStream(s, Charset.defaultCharset()),
                IOUtils.toInputStream(s, Charset.defaultCharset()), Charset.defaultCharset());

        for (Token t : result.getStream().getTokens()) {
            if (t.getType() == Token.EOF) {
                continue;
            }
            int[] start = result.getLineAndColumn(t.getStartIndex());
            int[] end = result.getLineAndColumn(t.getStopIndex());
            assertThat(start).isNotNull();
            assertThat(end).isNotNull();
            assertThat(t.getLine()).isEqualTo(start[0]);
            assertThat(t.getCharPositionInLine()).isEqualTo(start[1]);
        }
    }

}
