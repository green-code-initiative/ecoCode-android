package io.ecocode.ios.antlr;

import org.junit.Test;
import java.nio.charset.Charset;
import static org.assertj.core.api.Assertions.assertThat;

public class SourceLinesProviderTest {

    private static final String MAIN_SRC = "/antlr/main.swift";

    @Test
    public void getLines() {


        SourceLinesProvider provider = new SourceLinesProvider();
        SourceLine[] lines = provider.getLines(this.getClass().getResourceAsStream(MAIN_SRC), Charset.defaultCharset());
        assertThat(lines).hasSize(6);
    }
}
