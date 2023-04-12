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
package io.ecocode.ios.swift.checks;

import io.ecocode.ios.swift.antlr.generated.Swift5Parser;
import org.antlr.v4.runtime.tree.ParseTree;

public class CheckHelper {
    public static Boolean isImportExisting(ParseTree tree, String importName) {
        if (tree instanceof Swift5Parser.Import_declarationContext) {
            Swift5Parser.Import_declarationContext id = (Swift5Parser.Import_declarationContext) tree;
            if(id.getText().contains(importName)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
