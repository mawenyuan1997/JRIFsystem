package jrif.ast;

import KAT.KATexpression;
import polyglot.ast.Node_c;
import polyglot.util.Position;

public abstract class KatExprNode extends Node_c {
    KATexpression policy;
    public KatExprNode(Position pos) {
        super(pos,null);
    }

    public abstract void disambiguate();
}
