package jrif.ast;

import jrif.types.KatExprType;
import polyglot.ast.Node_c;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;

public abstract class KatExprNode_c extends Node_c implements KatExprNode {
    private static final long serialVersionUID = SerialVersionUID.generate();
    KatExprType type;
    public KatExprNode_c(Position pos) {
        super(pos,null);
    }
    public KatExprType getType() { return type; }
}
