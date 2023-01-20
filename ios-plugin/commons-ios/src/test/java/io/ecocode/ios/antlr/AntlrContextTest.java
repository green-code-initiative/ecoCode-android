package io.ecocode.ios.antlr;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.sonar.api.batch.fs.InputFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;

public class AntlrContextTest {

    @Test
    public void loadFromStreams() throws IOException {

        AntlrContext context = new AntlrContext() {
            @Override
            public void loadFromStreams(InputFile inputFile, InputStream file, InputStream linesStream, Charset charset) throws IOException {

                SourceLine[] lines = new SourceLine[1];
                lines[0] = new SourceLine(0, 0, 0, 0);
                this.setLines(lines);
                this.setRoot(null);
                this.setStream(null);
                this.setFile(null);
            }
        };

        // Load an empty content as loadFromStreams is mocked in test
        context.loadFromStreams(null, IOUtils.toInputStream("", Charset.defaultCharset()),
                IOUtils.toInputStream("", Charset.defaultCharset()), Charset.defaultCharset());

        assertThat(context.getLines()).hasSize(1);
        assertThat(context.getRoot()).isNull();
        assertThat(context.getStream()).isNull();
        assertThat(context.getFile()).isNull();

    }
}
