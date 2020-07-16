/*
 * This file is part of the Polyglot extensible compiler framework.
 *
 * Copyright (c) 2000-2006 Polyglot project group, Cornell University
 * 
 */

package pao.ast;

import polyglot.ast.NodeFactory_c;

/**
 * <code>NodeFactory</code> implementation for the pao extension.
 * The node factory is responsible for creating AST nodes. 
 */
public class PaoNodeFactory_c extends NodeFactory_c {
    public PaoNodeFactory_c() {
        // Set the ExtFactory to be PaoExtFactory_c.
        // this will ensure that the correct Ext
        // objects are created for the pao extension.
        super(PaoLang.instance, new PaoExtFactory_c());
    }
}
