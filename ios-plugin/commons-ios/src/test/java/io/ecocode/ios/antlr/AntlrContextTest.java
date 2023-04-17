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
