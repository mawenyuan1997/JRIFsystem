package jrif.ast;

import KAT.KATexpression;
import jrif.types.KatExprType;
import polyglot.ast.Node_c;
import polyglot.util.Position;

public abstract class KatExprNode extends Node_c {
    KatExprType type;
    public KatExprNode(Position pos) {
        super(pos,null);
    }
    public KatExprType getType() { return type; }
}
