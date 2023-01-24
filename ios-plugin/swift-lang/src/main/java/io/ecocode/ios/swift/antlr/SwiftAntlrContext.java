/*
 * ecoCode iOS plugin - Help the earth, adopt this green plugin for your applications
 * Copyright © 2022 CNumR (https://www.ecocode.io/)
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
import io.ecocode.ios.antlr.SourceLine;
import io.ecocode.ios.antlr.SourceLinesProvider;
import io.ecocode.ios.swift.antlr.generated.Swift5Lexer;
import io.ecocode.ios.swift.antlr.generated.Swift5Parser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.sonar.api.batch.fs.InputFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class SwiftAntlrContext extends AntlrContext {

    @Override
    public void loadFromStreams(InputFile inputFile, InputStream file, InputStream linesStream, Charset charset) throws IOException {
        final SourceLinesProvider linesProvider = new SourceLinesProvider();
        final CharStream charStream = CharStreams.fromStream(file, charset);
        final Swift5Lexer lexer = new Swift5Lexer(charStream);
        lexer.removeErrorListeners();
        final CommonTokenStream stream = new CommonTokenStream(lexer);
        stream.fill();
        final Swift5Parser parser = new Swift5Parser(stream);
        parser.removeErrorListeners();
        final Swift5Parser.Top_levelContext root =  parser.top_level();
        final SourceLine[] lines = linesProvider.getLines(linesStream, charset);

        this.setFile(inputFile);
        this.setStream(stream);
        this.setLines(lines);
        this.setRoot(root);
    }

}
