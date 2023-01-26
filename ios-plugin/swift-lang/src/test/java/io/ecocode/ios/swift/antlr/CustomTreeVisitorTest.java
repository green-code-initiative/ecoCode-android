/*
 * ecoCode iOS plugin - Help the earth, adopt this green plugin for your applications
 * Copyright © 2022 Green code Initiative (https://www.ecocode.io/)
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
package io.ecocode.ios.swift.antlr;

import io.ecocode.ios.antlr.AntlrContext;
import io.ecocode.ios.antlr.ParseTreeItemVisitor;
import io.ecocode.ios.swift.antlr.CustomTreeVisitor;
import io.ecocode.ios.swift.antlr.SwiftAntlrContext;
import io.ecocode.ios.swift.antlr.generated.Swift5Lexer;
import io.ecocode.ios.swift.antlr.generated.Swift5Parser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.mockito.Mock;
import org.sonar.api.batch.sensor.SensorContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomTreeVisitorTest {

    private static final String MAIN_SRC = "/main.swift";

    @Mock
    private SensorContext sensorContext;

    @Test
    public void visit() throws IOException {

        final CharStream charStream = CharStreams.fromStream(Objects.requireNonNull(this.getClass().getResourceAsStream(MAIN_SRC)));
        final Swift5Lexer lexer = new Swift5Lexer(charStream);
        lexer.removeErrorListeners();
        final CommonTokenStream stream = new CommonTokenStream(lexer);
        stream.fill();
        final Swift5Parser parser = new Swift5Parser(stream);
        parser.removeErrorListeners();
        final ParseTree root = parser.top_level();

        CustomTreeVisitor customTreeVisitor = new CustomTreeVisitor(new ParseTreeItemVisitor() {
            @Override
            public void apply(ParseTree tree) {

            }

            @Override
            public void fillContext(SensorContext context, AntlrContext antlrContext) {
                assertThat(antlrContext.getTokens()).hasSize(30);
            }


        });

        SwiftAntlrContext antlrContext = new SwiftAntlrContext();
        String text = IOUtils.toString(Objects.requireNonNull(this.getClass().getResourceAsStream(MAIN_SRC)), StandardCharsets.UTF_8);
        antlrContext.loadFromStreams(null, IOUtils.toInputStream(text, Charset.defaultCharset()),
                IOUtils.toInputStream(text, Charset.defaultCharset()), Charset.defaultCharset());

        customTreeVisitor.visit(root);
        customTreeVisitor.fillContext(sensorContext, antlrContext);
    }

}